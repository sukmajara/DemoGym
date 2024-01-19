package com.demo.gym.DTO;

public class ResponsePaymentClassDTO {
    private String resCode;
    private String resMsg;

    public ResponsePaymentClassDTO() {
    }

    public ResponsePaymentClassDTO(String resCode, String resMsg) {
        this.resCode = resCode;
        this.resMsg = resMsg;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }
}
