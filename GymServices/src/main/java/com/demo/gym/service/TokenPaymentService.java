package com.demo.gym.service;

import com.demo.gym.domain.TokenPayment;
import com.demo.gym.domain.TokenUser;

public interface TokenPaymentService {
    public void saveConfirmationToken(TokenPayment tokenPayment);

}
