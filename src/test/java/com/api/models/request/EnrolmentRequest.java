package com.api.models.request;

public class EnrolmentRequest {
    private String username;
    private String courseCode;

    public EnrolmentRequest(){
    }

    public EnrolmentRequest(String username, String courseCode) {
        this.username = username;
        this.courseCode = courseCode;
    }

    public String getUsername() {
        return username;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public String toString() {
        return "EnrolmentRequest{" +
                "username='" + username + '\'' +
                ", courseCode='" + courseCode + '\'' +
                '}';
    }
}
