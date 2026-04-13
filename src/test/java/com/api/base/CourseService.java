package com.api.base;

import com.api.models.request.AddNewCourseRequest;
import io.restassured.response.Response;

public class CourseService extends BaseService {

    private static final String BASE_PATH = "/courses";

    public Response searchCourseByTitle(String title) {
        return getRequest(BASE_PATH + "/title/" + title, null);
    }

    public Response checkCourseSlot(String courseCode) {
        return getRequest(BASE_PATH + "/availability/" + courseCode, null);
    }

    public Response addNewCourse(String token, AddNewCourseRequest payload) {
        return postRequest(payload, BASE_PATH, token);
    }

    public Response searchAllCourse() {
        return getRequest(BASE_PATH + "/all", null);
    }
}