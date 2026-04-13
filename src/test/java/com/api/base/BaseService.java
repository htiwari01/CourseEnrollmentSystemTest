package com.api.base;

import com.api.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService {
    private static final String BASE_URL = ConfigReader.get("base_url");

    private RequestSpecification getRequestSpec(String token) {
        RequestSpecification spec = RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
        if (token != null && !token.isEmpty()) {
            spec.header("Authorization", "Bearer " + token);
        }
        return spec;
    }

    protected Response postRequest(Object payload, String endpoint, String token) {
        return getRequestSpec(token).body(payload).post(endpoint);
    }

    protected Response getRequest(String endpoint, String token) {
        return getRequestSpec(token).get(endpoint);
    }
}