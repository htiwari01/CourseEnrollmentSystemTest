package com.api.test;

import com.api.base.HealthCheckService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void verifyServiceHealth() {

        HealthCheckService healthCheckService = new HealthCheckService();
        Response response = healthCheckService.checkHealth();
        Assert.assertEquals(response.statusCode(), 200, "Service is DOWN - Aborting tests");
        String status = response.jsonPath().getString("status");
        Assert.assertEquals(status, "Server is running", "Service is not healthy");
    }
}