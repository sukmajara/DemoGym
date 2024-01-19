package com.demo.gym.serviceImpl;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.gym.DTO.RequestConfirmTokenDTO;
import com.demo.gym.DTO.RequestGetUserDTO;
import com.demo.gym.DTO.RequestLoginUserDTO;
import com.demo.gym.DTO.RequestRegisterUserDTO;
import com.demo.gym.DTO.RequestResendTokenDTO;
import com.demo.gym.DTO.RequestResetPasswordDTO;
import com.demo.gym.DTO.ResponseConfimTokenDTO;
import com.demo.gym.DTO.ResponseGetUserDTO;
import com.demo.gym.DTO.ResponseLoginUserDTO;
import com.demo.gym.DTO.ResponseRegisterUserDTO;
import com.demo.gym.DTO.ResponseResendTokenDTO;
import com.demo.gym.DTO.ResponseResetPasswordDTO;
import com.demo.gym.domain.TokenUser;
import com.demo.gym.domain.User;
import com.demo.gym.repository.TokenUserRepository;
import com.demo.gym.repository.UserRepository;
import com.demo.gym.service.AuthencticationService;
import com.demo.gym.service.EmailService;
import com.demo.gym.service.TokenUserService;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthencticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenUserService tokenUserService;

    @Autowired
    private TokenUserRepository tokenUserRepository;

    @Autowired
    private TokenService tokenService;

    public ResponseRegisterUserDTO registerUser(RequestRegisterUserDTO userRegister) {
        User email = userRepository.findEmail(userRegister.getEmail());
        String password = passwordEncoder.encode(userRegister.getPassword());
        String key = userRegister.getCardNumber();
        try {
            if (email == null) {

                Cipher cipher = Cipher.getInstance("AES");
                byte[] keyBytes = Arrays.copyOf(key.getBytes(StandardCharsets.UTF_8), 16);
                SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

                byte[] encryptedCardNumber = cipher.doFinal(userRegister.getCardNumber().getBytes());
                byte[] encryptedCVV = cipher.doFinal(userRegister.getCVV().getBytes());
                byte[] encryptedExpDate = cipher.doFinal(userRegister.getExpDate().getBytes());
                byte[] encryptednameCardHolder = cipher.doFinal(userRegister.getNameCardHolder().getBytes());

                User data = new User();
                data.setName(userRegister.getName());
                data.setPassword(password);
                data.setEmail(userRegister.getEmail());
                data.setPhoneNumber(userRegister.getPhoneNumber());
                data.setCardNumber(encryptedCardNumber.toString());
                data.setCVV(encryptedCVV.toString());
                data.setExpDate(encryptedExpDate.toString());
                data.setNameCardHolder(encryptednameCardHolder.toString());
                data.setStatus("BELUM TERVALIDASI");

                String tokenValue = UUID.randomUUID().toString().substring(0, 5);
                TokenUser tokenUser = new TokenUser(data, tokenValue, LocalDateTime.now(), LocalDateTime.now().plusMinutes(5));

                emailService.sendemail(userRegister.getEmail(), "OTP Login User GYM", "OTP = " + tokenValue);

                userRepository.save(data);
                tokenUserService.saveConfirmationToken(tokenUser);

                return new ResponseRegisterUserDTO(HttpStatus.OK.toString().substring(0, 3),
                        "Success Create User " + userRegister.getEmail());
            } else {
                return new ResponseRegisterUserDTO(HttpStatus.BAD_REQUEST.toString().substring(0, 3),
                        "Email Already Registered" + userRegister.getEmail());
            }

        } catch (Exception e) {
            return new ResponseRegisterUserDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString().substring(0, 3),
                    "Error Create User (" + e.fillInStackTrace() + ")");
        }
    }

    public ResponseLoginUserDTO loginUser(RequestLoginUserDTO loginRequest) {
        try {
            User data = userRepository.findByEmail(loginRequest.getEmail()).get();

            if (data.equals(null)) {
                return new ResponseLoginUserDTO(HttpStatus.NOT_FOUND.toString().substring(0, 3), "USER TIDAK TERDAFTAR",
                        loginRequest.getEmail(), null);

            } else if (data.getStatus().equals("BELUM TERVALIDASI")) {
                return new ResponseLoginUserDTO(HttpStatus.UNAUTHORIZED.toString().substring(0, 3),
                        "VALIDASI EMAIL TERLEBIH DAHULU", loginRequest.getEmail(), null);
            }

            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            String token = tokenService.generateJwt(auth);
            return new ResponseLoginUserDTO(HttpStatus.OK.toString().substring(0, 3), "SUCCESS",
                    loginRequest.getEmail(), token);
        } catch (AuthenticationException e) {
            return new ResponseLoginUserDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString().substring(0, 3), "ERROR " + e,
                    null, null);
        }
    }

    @Override
    public ResponseEntity<ResponseGetUserDTO> getUser(RequestGetUserDTO request) {
        try {

            User data = userRepository.findEmail(request.getEmail());

            if (data == null) {
                return new ResponseEntity<>(
                        new ResponseGetUserDTO("TIDAK TERDAFTAR", HttpStatus.NOT_FOUND.toString().substring(0, 3)),
                        HttpStatus.NOT_FOUND);
            } else if (data.getStatus().equals("BELUM TERVALIDASI")) {
                return new ResponseEntity<>(
                        new ResponseGetUserDTO("BELUM TERVALIDASI", HttpStatus.OK.toString().substring(0, 3)),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        new ResponseGetUserDTO("TERDAFTAR", HttpStatus.OK.toString().substring(0, 3)), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseGetUserDTO(e.getLocalizedMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR.toString().substring(0, 3)),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<ResponseConfimTokenDTO> confirmToken(RequestConfirmTokenDTO request) {
        try {

            TokenUser data = tokenUserRepository.findByToken(request.getToken()).get();
            User user = userRepository.findByEmail(data.getUser().getEmail()).get();

            if (data.equals(null)) {
                return new ResponseEntity<>(
                        new ResponseConfimTokenDTO(HttpStatus.NOT_FOUND.toString().substring(0, 3), "TOKEN SALAH"),
                        HttpStatus.NOT_FOUND);
            } else if (data.getExpat().isBefore(LocalDateTime.now())) {
                return new ResponseEntity<>(
                        new ResponseConfimTokenDTO(HttpStatus.FORBIDDEN.toString().substring(0, 3), "TOKEN EXPIRED"),
                        HttpStatus.FORBIDDEN);
            } else if (data.getConfirmat() != null) {
                return new ResponseEntity<>(
                        new ResponseConfimTokenDTO(HttpStatus.OK.toString().substring(0, 3), "TOKEN SUDAH DICONFIRM"),
                        HttpStatus.OK);
            } else {
                data.setConfirmat(LocalDateTime.now());
                user.setStatus("TERDAFTAR");

                tokenUserRepository.save(data);
                userRepository.save(user);
                return new ResponseEntity<>(
                        new ResponseConfimTokenDTO(HttpStatus.OK.toString().substring(0, 3), "CONFIRM SUCCESS"),
                        HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseConfimTokenDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString().substring(0, 3),
                            e.getLocalizedMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseResendTokenDTO> resendToken(RequestResendTokenDTO request) {
        try {

            String tokenValue = UUID.randomUUID().toString().substring(0, 5);
            User userdata = userRepository.findByEmail(request.getEmail()).get();
            TokenUser data = tokenUserRepository.FindNewestToken(userdata.getId()).get();

            if (data.getExpat().isAfter(LocalDateTime.now()) && data.getConfirmat() == null) {
                return new ResponseEntity<>(
                        new ResponseResendTokenDTO(HttpStatus.OK.toString().substring(0, 3), "MOHON CEK EMAIL ANDA"),
                        HttpStatus.OK);
            } else {
                data = new TokenUser(userdata, tokenValue, LocalDateTime.now(), LocalDateTime.now().plusMinutes(5));

                emailService.sendemail(request.getEmail(), "OTP Login User GYM", "OTP = " + tokenValue);

                tokenUserService.saveConfirmationToken(data);
                return new ResponseEntity<>(new ResponseResendTokenDTO(HttpStatus.OK.toString().substring(0, 3),
                        "TOKEN TELAH DIKIRIMKAN KE EMAIL " + request.getEmail()), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseResendTokenDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString().substring(0, 3),
                            e.getLocalizedMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseResetPasswordDTO> resetPassword(RequestResetPasswordDTO request) {
        try {
            String password = passwordEncoder.encode(request.getPassword());
            User data = userRepository.findByEmail(request.getEmail()).get();

            String tokenValue = UUID.randomUUID().toString().substring(0, 5);
            TokenUser tokenUser = new TokenUser(data, tokenValue, LocalDateTime.now(), LocalDateTime.now().plusMinutes(5));

            data.setPassword(password);
            data.setStatus("BELUM TERVALIDASI");

            emailService.sendemail(request.getEmail(), "OTP Login User GYM", "OTP = " + tokenValue);
            tokenUserService.saveConfirmationToken(tokenUser);
            userRepository.save(data);

            return new ResponseEntity<>(
                    new ResponseResetPasswordDTO(HttpStatus.OK.toString().substring(0, 3), "RESET PASSWORD SUCCESS"),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseResetPasswordDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString().substring(0, 3), "ERROR"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
