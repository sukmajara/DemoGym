package com.demo.gympayment.dto;

public class ResponsePaymentDTO {

    private String resMsg;
    private String resCode;

    public ResponsePaymentDTO() {
    }

    public ResponsePaymentDTO(String resMsg, String resCode) {
        this.resMsg = resMsg;
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }
}
