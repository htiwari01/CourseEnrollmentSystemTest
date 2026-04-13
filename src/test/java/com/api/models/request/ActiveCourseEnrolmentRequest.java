package com.api.models.request;

public class ActiveCourseEnrolmentRequest {
    private String username;

    public ActiveCourseEnrolmentRequest(){

    }

    public ActiveCourseEnrolmentRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ActiveCourseEnrolmentRequest{" +
                "username='" + username + '\'' +
                '}';
    }
}
