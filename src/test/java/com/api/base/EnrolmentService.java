package com.api.base;

import com.api.models.request.ActiveCourseEnrolmentRequest;
import com.api.models.request.EnrolmentRequest;
import io.restassured.response.Response;

public class EnrolmentService extends BaseService{
    private static final String BASE_PATH = "/enrolments";

    public Response courseEnrolment(String token, EnrolmentRequest payload){
        setAuthToken(token);
        return postRequest(payload, BASE_PATH + "/enrol/");
    }

    public Response activeCourseEnrolment(String token, ActiveCourseEnrolmentRequest payload){
        setAuthToken(token);
        return postRequest(payload, BASE_PATH + "/active/");
    }

    public Response dropCourse(String token, EnrolmentRequest payload){
        setAuthToken(token);
        return postRequest(payload, BASE_PATH + "/drop/");
    }


}
