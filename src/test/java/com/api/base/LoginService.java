package com.api.base;

import com.api.models.request.LoginRequest;
import io.restassured.response.Response;

public class LoginService extends BaseService {

    private static final String STUDENT_LOGIN_PATH = "/student/login";
    private static final String INSTRUCTOR_LOGIN_PATH = "/instructor/login";

    public Response studentLogin(LoginRequest payload) {
        return postRequest(payload, STUDENT_LOGIN_PATH, null);
    }

    public Response instructorLogin(LoginRequest payload) {
        return postRequest(payload, INSTRUCTOR_LOGIN_PATH, null);
    }
}