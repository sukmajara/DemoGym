package com.demo.gym.DTO;

public class ResponseResendTokenOTPDTO {

    String resCode;
    String resMsg;

    public ResponseResendTokenOTPDTO() {
    }
    public ResponseResendTokenOTPDTO(String resCode, String resMsg) {
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
