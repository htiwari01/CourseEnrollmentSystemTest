package com.api.base;

import io.restassured.response.Response;

public class HealthCheckService extends BaseService {

    public Response checkHealth() {
        return getRequest("/status", null);
    }
}
