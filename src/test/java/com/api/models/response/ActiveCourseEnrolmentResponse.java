package com.api.models.response;

import java.util.Date;

public class ActiveCourseEnrolmentResponse {
    private  String username;
    private  String courseCode;
    private  String courseTitle;
    private Date enrolDate;
    private Date completionDate;
    private  String status;

    public ActiveCourseEnrolmentResponse(){
    }

    public String getUsername() {
        return username;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public Date getEnrolDate() {
        return enrolDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public void setEnrolDate(Date enrolDate) {
        this.enrolDate = enrolDate;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public String toString() {
        return "ActiveCourseEnrolmentResponse{" +
                "username='" + username + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", enrolDate=" + enrolDate +
                ", completionDate=" + completionDate +
                ", status='" + status + '\'' +
                '}';
    }
}
