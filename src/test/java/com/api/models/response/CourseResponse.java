package com.api.models.response;

public class CourseResponse {
    private String _id;
    private String title;
    private String instructor;
    private String courseCode;
    private String category;
    private int totalCapacity;
    private int availableSlots;
    private String startDate;
    private String endDate;

    public CourseResponse(){
    }

    public CourseResponse(String _id, String endDate, String startDate, int availableSlots, int totalCapacity, String category, String courseCode, String instructor, String title) {
        this._id = _id;
        this.endDate = endDate;
        this.startDate = startDate;
        this.availableSlots = availableSlots;
        this.totalCapacity = totalCapacity;
        this.category = category;
        this.courseCode = courseCode;
        this.instructor = instructor;
        this.title = title;
    }

    public String get_id() {
        return _id;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
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

    public String getTitle() {
        return title;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "CourseResponse{" +
                "_id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", instructor='" + instructor + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", category='" + category + '\'' +
                ", totalCapacity=" + totalCapacity +
                ", availableSlots=" + availableSlots +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
