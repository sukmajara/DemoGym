package com.demo.gym.controller;

import com.demo.gym.DTO.*;
import com.demo.gym.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gym/payment")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/obes")
    public ResponseEntity<ResponsePaymentClassDTO> paymentObes(@RequestBody RequestPaymentClassDTO request) {
        return transactionService.paymentObes(request);
    }

    @PostMapping("/nonobes")
    public ResponseEntity<ResponsePaymentClassDTO> nonobes(@RequestBody RequestPaymentClassDTO request) {
        return transactionService.nonobes(request);
    }
    @PostMapping("/confirm")
    public ResponseEntity<ResponseConfirmOTPDTO> confirmOTP(@RequestBody RequestConfirmOTPDTO request) {
        return transactionService.confirmOTP(request);
    }

    @PostMapping("/resendtoken")
    public ResponseEntity<ResponseResendTokenOTPDTO> resendToken(@RequestBody RequestResendTokenOTPDTO request) {
        return transactionService.resendToken(request);
    }


}
