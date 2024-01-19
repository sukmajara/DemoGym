package com.demo.gym.serviceImpl;

import com.demo.gym.DTO.*;
import com.demo.gym.domain.*;
import com.demo.gym.event.PaymentEvent;
import com.demo.gym.repository.*;
import com.demo.gym.service.EmailService;
import com.demo.gym.service.TokenPaymentService;
import com.demo.gym.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private TokenPaymentService tokenPaymentService;
    @Autowired
    TokenPaymentRepository tokenPaymentRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    GymClassRepository gymClassRepository;
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public ResponseEntity<ResponsePaymentClassDTO> paymentObes(RequestPaymentClassDTO request) {


        User data = userRepository.findByEmail(request.getEmail()).get();
        kafkaTemplate.send("paymentClass", new PaymentEvent(data));
        return new ResponseEntity<>(new ResponsePaymentClassDTO(HttpStatus.OK.toString().substring(0, 3), "PROCESSING"), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<ResponsePaymentClassDTO> nonobes(RequestPaymentClassDTO request) {
        try {
            User data = userRepository.findByEmail(request.getEmail()).get();
            Product product = productRepository.findById(Long.valueOf(2)).get();
            GymClass gymClass = gymClassRepository.findUser(data.getId());

            Transaction transaction = new Transaction();
            transaction.setProduct(product);
            transaction.setUser(data);
            transaction.setStatus(0);
            transaction.setGymClass(gymClass);
            transactionRepository.save(transaction);

            String tokenValue = UUID.randomUUID().toString().substring(0, 5);
            TokenPayment tokenPayment = new TokenPayment(data, tokenValue, LocalDateTime.now(), LocalDateTime.now().plusMinutes(5));

            emailService.sendemail(request.getEmail(), "OTP Login User GYM", "OTP = " + tokenValue);
            tokenPaymentService.saveConfirmationToken(tokenPayment);
            return new ResponseEntity<>(new ResponsePaymentClassDTO(HttpStatus.OK.toString().substring(0, 3)
                    , "MOHON CEK EMAIL ANDA UNTUK CONFIM PEMBAYAWAN"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ResponsePaymentClassDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString().substring(0, 3)
                    , e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseConfirmOTPDTO> confirmOTP(RequestConfirmOTPDTO request) {
        try {

            TokenPayment data = tokenPaymentRepository.findByToken(request.getToken()).get();
            User user = userRepository.findByEmail(data.getUser().getEmail()).get();
            Transaction transaction = transactionRepository.findbyUserid(user.getId());
            GymClass gymClass = gymClassRepository.findById(transaction.getGymClass().getId()).get();

            if (data.equals(null)) {
                return new ResponseEntity<>(
                        new ResponseConfirmOTPDTO(HttpStatus.NOT_FOUND.toString().substring(0, 3), "TOKEN SALAH"),
                        HttpStatus.NOT_FOUND);
            } else if (data.getExpat().isBefore(LocalDateTime.now())) {
                return new ResponseEntity<>(
                        new ResponseConfirmOTPDTO(HttpStatus.FORBIDDEN.toString().substring(0, 3), "TOKEN EXPIRED"),
                        HttpStatus.FORBIDDEN);
            } else if (data.getConfirmat() != null) {
                return new ResponseEntity<>(
                        new ResponseConfirmOTPDTO(HttpStatus.OK.toString().substring(0, 3), "TOKEN SUDAH DICONFIRM"),
                        HttpStatus.OK);
            } else {
                data.setConfirmat(LocalDateTime.now());
                transaction.setStatus(1);

                gymClass.setStatus("AKTIF");

                gymClassRepository.save(gymClass);
                tokenPaymentRepository.save(data);
                transactionRepository.save(transaction);
                return new ResponseEntity<>(
                        new ResponseConfirmOTPDTO(HttpStatus.OK.toString().substring(0, 3), "CONFIRM SUCCESS"),
                        HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseConfirmOTPDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString().substring(0, 3),
                            e.getLocalizedMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseResendTokenOTPDTO> resendToken(RequestResendTokenOTPDTO request) {
        try {

            String tokenValue = UUID.randomUUID().toString().substring(0, 5);
            User userdata = userRepository.findByEmail(request.getEmail()).get();
            TokenPayment data = tokenPaymentRepository.findNewestToken(userdata.getId()).get();

            if (data.getExpat().isAfter(LocalDateTime.now()) && data.getConfirmat() == null) {
                return new ResponseEntity<>(
                        new ResponseResendTokenOTPDTO(HttpStatus.OK.toString().substring(0, 3), "MOHON CEK EMAIL ANDA"),
                        HttpStatus.OK);
            } else {
                data = new TokenPayment(userdata, tokenValue, LocalDateTime.now(), LocalDateTime.now().plusMinutes(5));

                emailService.sendemail(request.getEmail(), "OTP Login User GYM", "OTP = " + tokenValue);

                tokenPaymentService.saveConfirmationToken(data);
                return new ResponseEntity<>(new ResponseResendTokenOTPDTO(HttpStatus.OK.toString().substring(0, 3),
                        "TOKEN TELAH DIKIRIMKAN KE EMAIL " + request.getEmail()), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseResendTokenOTPDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString().substring(0, 3),
                            e.getLocalizedMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
