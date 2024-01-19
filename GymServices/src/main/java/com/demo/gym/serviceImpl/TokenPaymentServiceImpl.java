package com.demo.gym.serviceImpl;

import com.demo.gym.domain.TokenPayment;
import com.demo.gym.domain.TokenUser;
import com.demo.gym.repository.TokenPaymentRepository;
import com.demo.gym.service.TokenPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenPaymentServiceImpl implements TokenPaymentService {
    @Autowired
    private TokenPaymentRepository tokenPaymentRepository;

    public void saveConfirmationToken(TokenPayment tokenpayment) {
        tokenPaymentRepository.save(tokenpayment);
    }
}
