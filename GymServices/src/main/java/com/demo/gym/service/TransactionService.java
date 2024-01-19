package com.demo.gym.service;

import com.demo.gym.DTO.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface TransactionService {
    public ResponseEntity<ResponsePaymentClassDTO> paymentObes(RequestPaymentClassDTO request);

    public ResponseEntity<ResponsePaymentClassDTO> nonobes(RequestPaymentClassDTO request);

    public ResponseEntity<ResponseConfirmOTPDTO> confirmOTP(RequestConfirmOTPDTO request);

    public ResponseEntity<ResponseResendTokenOTPDTO> resendToken(@RequestBody RequestResendTokenOTPDTO request);


    }
