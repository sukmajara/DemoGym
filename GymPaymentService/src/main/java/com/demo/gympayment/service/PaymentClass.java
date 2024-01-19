package com.demo.gympayment.service;

import com.demo.gympayment.dto.RequestPaymentDTO;
import com.demo.gympayment.dto.ResponsePaymentDTO;
import org.springframework.http.ResponseEntity;

public interface PaymentClass {

    public  ResponseEntity<ResponsePaymentDTO> paymentCC(RequestPaymentDTO request);

}
