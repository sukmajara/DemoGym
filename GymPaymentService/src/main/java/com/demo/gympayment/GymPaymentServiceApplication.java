package com.demo.gympayment;

import com.demo.gympayment.dto.RequestPaymentDTO;
import com.demo.gympayment.dto.ResponsePaymentDTO;
import com.demo.gympayment.event.sendOtpEmail;
import com.demo.gympayment.service.PaymentClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class GymPaymentServiceApplication {

    @Autowired
    PaymentClass paymentClass;

    public static void main(String[] args) {
        SpringApplication.run(GymPaymentServiceApplication.class, args);
    }

    @KafkaListener(topics = "paymentClass")
    public ResponseEntity<ResponsePaymentDTO> paymentClass(RequestPaymentDTO request){
        return paymentClass.paymentCC(request);
    }

}
