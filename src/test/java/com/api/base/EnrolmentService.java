package com.api.base;

import com.api.models.request.ActiveCourseEnrolmentRequest;
import com.api.models.request.EnrolmentRequest;
import io.restassured.response.Response;

public class EnrolmentService extends BaseService {

    private static final String BASE_PATH = "/enrolments";

    public Response courseEnrolment(String token, EnrolmentRequest payload) {
        return postRequest(payload, BASE_PATH + "/enrol/", token);
    }

    public Response activeCourseEnrolment(String token, ActiveCourseEnrolmentRequest payload) {
        return postRequest(payload, BASE_PATH + "/active/", token);
    }

    public Response dropCourse(String token, EnrolmentRequest payload) {
        return postRequest(payload, BASE_PATH + "/drop/", token);
    }
}