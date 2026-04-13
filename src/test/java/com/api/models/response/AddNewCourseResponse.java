package com.api.models.response;

public class AddNewCourseResponse {
    private String message;
    private NewCourse newCourse;

    public AddNewCourseResponse(){

    }

    public AddNewCourseResponse(String message, NewCourse newCourse) {
        this.message = message;
        this.newCourse = newCourse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NewCourse getNewCourse() {
        return newCourse;
    }

    public void setNewCourse(NewCourse newCourse) {
        this.newCourse = newCourse;
    }

    @Override
    public String toString() {
        return "AddNewCourseResponse{" +
                "message='" + message + '\'' +
                ", newCourse='" + newCourse + '\'' +
                '}';
    }
}
