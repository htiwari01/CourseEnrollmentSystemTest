package com.api.models.response;

public class CourseAvailabilityResponse {
    String courseCode;
    String title;
    int availableSlots;

    public CourseAvailabilityResponse(){
    }

    public CourseAvailabilityResponse(String courseCode, int availableSlots, String title) {
        this.courseCode = courseCode;
        this.availableSlots = availableSlots;
        this.title = title;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

    @Override
    public String toString() {
        return "CourseAvailabilityResponse{" +
                "courseCode='" + courseCode + '\'' +
                ", title='" + title + '\'' +
                ", availableSlots=" + availableSlots +
                '}';
    }
}
