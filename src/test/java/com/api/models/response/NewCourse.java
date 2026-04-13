package com.api.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewCourse {
    private String title;
    private String instructor;
    private String courseCode;
    private String category;
    private int totalCapacity;
    private int availableSlots;
    private Date startDate;
    private Date endDate;
    private String _id;

    public NewCourse(){

    }

    public NewCourse(String title, String _id, Date endDate, Date startDate, int availableSlots, int totalCapacity, String category, String courseCode, String instructor) {
        this.title = title;
        this._id = _id;
        this.endDate = endDate;
        this.startDate = startDate;
        this.availableSlots = availableSlots;
        this.totalCapacity = totalCapacity;
        this.category = category;
        this.courseCode = courseCode;
        this.instructor = instructor;
    }

    public String getTitle() {
        return title;
    }

    public String get_id() {
        return _id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getAvailableSlots() {
        return availableSlots;
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

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "NewCourse{" +
                "title='" + title + '\'' +
                ", instructor='" + instructor + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", category='" + category + '\'' +
                ", totalCapacity=" + totalCapacity +
                ", availableSlots=" + availableSlots +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", _id='" + _id + '\'' +
                '}';
    }
}
