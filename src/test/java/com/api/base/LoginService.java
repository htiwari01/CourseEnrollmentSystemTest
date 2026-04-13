package com.api.base;

import com.api.models.request.LoginRequest;
import io.restassured.response.Response;

public class LoginService extends BaseService{

    public Response studentLogin(LoginRequest payload){
        return postRequest(payload, "/student/login");
    }

    public Response instructorLogin(LoginRequest payload){
        return postRequest(payload, "/instructor/login");
    }
}
