package com.api.models.response;

public class EnrolementResponse {
    private String message;
    private EnrolmentJson enrolmentJson;

    public EnrolementResponse(){
    }

    public EnrolementResponse(String message, EnrolmentJson enrolmentJson) {
        this.message = message;
        this.enrolmentJson = enrolmentJson;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EnrolmentJson getEnrolmentJson() {
        return enrolmentJson;
    }

    public void setEnrolmentJson(EnrolmentJson enrolmentJson) {
        this.enrolmentJson = enrolmentJson;
    }

    @Override
    public String toString() {
        return "EnrolementResponse{" +
                "message='" + message + '\'' +
                ", enrolmentJson=" + enrolmentJson +
                '}';
    }
}
