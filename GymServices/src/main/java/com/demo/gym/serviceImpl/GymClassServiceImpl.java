package com.demo.gym.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.gym.DTO.RequestCancelClassDTO;
import com.demo.gym.DTO.RequestInfoUserClassDTO;
import com.demo.gym.DTO.RequestRegisterClassDTO;
import com.demo.gym.DTO.RequestUpdateClassDTO;
import com.demo.gym.DTO.ResponseCancelClassDTO;
import com.demo.gym.DTO.ResponseInfoUserClassDTO;
import com.demo.gym.DTO.ResponseRegisterClassDTO;
import com.demo.gym.DTO.ResponseUpdateClassDTO;
import com.demo.gym.domain.GymClass;
import com.demo.gym.domain.Product;
import com.demo.gym.domain.User;
import com.demo.gym.repository.GymClassRepository;
import com.demo.gym.repository.ProductRepository;
import com.demo.gym.repository.UserRepository;
import com.demo.gym.service.GymClassService;

@Service
public class GymClassServiceImpl implements GymClassService {

	@Autowired
	GymClassRepository gymClassRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public ResponseEntity<ResponseRegisterClassDTO> registerClass(RequestRegisterClassDTO request) {
		try {
			Product product = productRepository.findByName(request.getProduct());
			User userData = userRepository.findEmail(request.getEmail());
			GymClass statuscancel = gymClassRepository.findUserStatusCancel(userData.getId(), product.getId());
			List<GymClass> classdata = gymClassRepository.findUserList(userData.getId());

			System.out.println(classdata.isEmpty());

			if (classdata.isEmpty()) {
				GymClass data = new GymClass();
				data.setProduct(product);
				data.setUser(userData);
				data.setQuota(10);
				data.setStatus("BELUM TERBAYAR");
				gymClassRepository.save(data);

				return new ResponseEntity<>(
						new ResponseRegisterClassDTO(HttpStatus.OK.toString().substring(0, 3), "HARAP LAKUKAN PEMBAYARAN"),
						HttpStatus.OK);
			} else if (statuscancel != null) {
				statuscancel.setProduct(product);
				statuscancel.setUser(userData);
				statuscancel.setQuota(10);
				statuscancel.setStatus("BELUM TERBAYAR");
				gymClassRepository.save(statuscancel);

				return new ResponseEntity<>(
						new ResponseRegisterClassDTO(HttpStatus.OK.toString().substring(0, 3), "HARAP LAKUKAN PEMBAYARAN"),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						new ResponseRegisterClassDTO(HttpStatus.UNAUTHORIZED.toString().substring(0, 3),
								"USER SUDAH TIDAK AKTIF"),
						HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(
					new ResponseRegisterClassDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString().substring(0, 3),
							e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<ResponseCancelClassDTO> cancelClass(RequestCancelClassDTO request) {

		try {

			Product product = productRepository.findByName(request.getProduct());
			User userData = userRepository.findEmail(request.getEmail());
			GymClass statusactive = gymClassRepository.findUserStatusActive(userData.getId(), product.getId());

			if (statusactive != null) {
				statusactive.setProduct(product);
				statusactive.setUser(userData);
				statusactive.setQuota(0);
				statusactive.setStatus("CANCEL");
				gymClassRepository.save(statusactive);

				return new ResponseEntity<>(
						new ResponseCancelClassDTO(HttpStatus.OK.toString().substring(0, 3), "USER TIDAK AKTIF"),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						new ResponseCancelClassDTO(HttpStatus.UNAUTHORIZED.toString().substring(0, 3),
								"USER SUDAH TIDAK AKTIF"),
						HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(
					new ResponseCancelClassDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString().substring(0, 3),
							e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseUpdateClassDTO> updateClass(RequestUpdateClassDTO request) {
		try {

			Product product = productRepository.findByName(request.getProduct());
			User userData = userRepository.findEmail(request.getEmail());
			GymClass statusactive = gymClassRepository.findUserStatusActive(userData.getId(), product.getId());

			int quota = statusactive.getQuota() + request.getQuota();
			statusactive.setQuota(quota);

			gymClassRepository.save(statusactive);

			return new ResponseEntity<>(new ResponseUpdateClassDTO(HttpStatus.OK.toString().substring(0, 3),
					"QUOTA BERHASIL DITAMBAHKAN", request.getProduct(), quota), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseUpdateClassDTO(HttpStatus.NOT_FOUND.toString().substring(0, 3),
					"DATA TIDAK DITEMUKAN", null, 0), HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public ResponseEntity<ResponseInfoUserClassDTO> infoUserClass(RequestInfoUserClassDTO request) {
		try {
			User userData = userRepository.findEmail(request.getEmail());
			List<GymClass> data = gymClassRepository.findUserList(userData.getId());
			return new ResponseEntity<>(
					new ResponseInfoUserClassDTO(HttpStatus.OK.toString().substring(0, 3), "SUCCESS",data.get(0).getStatus(),data.get(0).getUser().getEmail(),data.get(0).getQuota()),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseInfoUserClassDTO(HttpStatus.NOT_FOUND.toString().substring(0, 3),
					e.getLocalizedMessage(), null,null,0), HttpStatus.NOT_FOUND);

		}

	}

}
