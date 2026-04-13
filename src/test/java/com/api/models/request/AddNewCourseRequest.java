package com.api.models.request;

import java.util.Date;

public class AddNewCourseRequest {
    private String title;
    private String instructor;
    private String courseCode;
    private String category;
    private int totalCapacity;
    private Date startDate;
    private Date endDate;

    public AddNewCourseRequest(){

    }

    public AddNewCourseRequest(String title, Date endDate, Date startDate, int totalCapacity, String category, String courseCode, String instructor) {
        this.title = title;
        this.endDate = endDate;
        this.startDate = startDate;
        this.totalCapacity = totalCapacity;
        this.category = category;
        this.courseCode = courseCode;
        this.instructor = instructor;
    }

    public String getTitle() {
        return title;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public String getCategory() {
        return category;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "AddNewCourseRequest{" +
                "title='" + title + '\'' +
                ", instructor='" + instructor + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", category='" + category + '\'' +
                ", totalCapacity=" + totalCapacity +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public static class Builder{
        private String title;
        private String instructor;
        private String courseCode;
        private String category;
        private int totalCapacity;
        private Date startDate;
        private Date endDate;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder instructor(String instructor) {
            this.instructor = instructor;
            return this;
        }

        public Builder courseCode(String courseCode) {
            this.courseCode = courseCode;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder totalCapacity(int totalCapacity) {
            this.totalCapacity = totalCapacity;
            return this;
        }

        public Builder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public AddNewCourseRequest build(){
            return new AddNewCourseRequest(title, endDate, startDate, totalCapacity, category, courseCode, instructor);
        }

    }
}
