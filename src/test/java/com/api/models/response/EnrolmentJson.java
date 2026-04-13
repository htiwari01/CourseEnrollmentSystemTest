package com.api.models.response;

import com.api.models.request.EnrolmentRequest;

import java.util.Date;

public class EnrolmentJson {
    private String username;
    private String courseCode;
    private String courseTitle;
    private Date enrolDate;
    private Date completionDate; // can be null
    private String status;

    public EnrolmentJson(){
    }

    public EnrolmentJson(String username, String status, Date completionDate, String courseTitle, String courseCode, Date enrolDate) {
        this.username = username;
        this.status = status;
        this.completionDate = completionDate;
        this.courseTitle = courseTitle;
        this.courseCode = courseCode;
        this.enrolDate = enrolDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Date getEnrolDate() {
        return enrolDate;
    }

    public void setEnrolDate(Date enrolDate) {
        this.enrolDate = enrolDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EnrolmentJson{" +
                "username='" + username + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", enrolDate=" + enrolDate +
                ", completionDate=" + completionDate +
                ", status='" + status + '\'' +
                '}';
    }
}
