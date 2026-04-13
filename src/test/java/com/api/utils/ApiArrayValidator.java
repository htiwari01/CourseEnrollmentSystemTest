package com.api.utils;

import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;

public class ApiArrayValidator {

    private final Response response;

    public ApiArrayValidator(Response response) {
        this.response = response;
    }

    public List<Map<String, Object>> asList() {
        return response.jsonPath().getList("$");
    }
}