package com.demo.gympayment.serviceImpl;

import com.demo.gympayment.dto.RequestPaymentDTO;
import com.demo.gympayment.dto.ResponsePaymentDTO;
import com.demo.gympayment.service.PaymentClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentClassImpl implements PaymentClass {

    @Override
    public ResponseEntity<ResponsePaymentDTO> paymentCC(RequestPaymentDTO request) {

        return new ResponseEntity<>(new ResponsePaymentDTO(HttpStatus.OK.toString().substring(0,3),"SUCCESS"), HttpStatus.OK);
    }
}
