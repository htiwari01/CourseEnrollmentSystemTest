package com.api.test;

import com.api.base.CourseService;
import com.api.base.LoginService;
import com.api.models.request.AddNewCourseRequest;
import com.api.models.request.LoginRequest;
import com.api.models.response.AddNewCourseResponse;
import com.api.models.response.CourseResponse;
import com.api.models.response.LoginResponse;
import com.api.utils.ConfigReader;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CourseJourneyTest {

    @Test(description = "Create, search and validate course journey")
    public void courseJourneyTest() {
        LoginService loginService = new LoginService();
        CourseService courseService = new CourseService();
        Response response;

        //Instructor Login
        LoginRequest loginRequest = new LoginRequest(ConfigReader.get("INSTRUCTOR_USERNAME"), ConfigReader.get("PASSWORD"));
        response = loginService.instructorLogin(loginRequest);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.time() < 30000);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        String token = loginResponse.getToken();
        Assert.assertNotNull(token);

        //Course Data
        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        Date courseStartDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date courseEndDate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        String courseCode = "AWSCloud_" + UUID.randomUUID().toString().substring(0, 6);
        String title = "AWS Cloud Course " + (int) (Math.random() * 10000);
        String instructor = "instructor_" + (int) (Math.random() * 10000);

        AddNewCourseRequest request = new AddNewCourseRequest.Builder()
                        .title(title)
                        .instructor(instructor)
                        .courseCode(courseCode)
                        .category("Cloud")
                        .totalCapacity(30)
                        .startDate(courseStartDate)
                        .endDate(courseEndDate)
                        .build();

        //Create a course
        response = courseService.addNewCourse(token, request);
        Assert.assertEquals(response.statusCode(), 201);
        AddNewCourseResponse createResponse = response.as(AddNewCourseResponse.class);
        Assert.assertEquals(createResponse.getMessage(), "Course added successfully");
        String courseId = createResponse.getNewCourse().get_id();
        Assert.assertNotNull(courseId);

        //Search All course
        response = courseService.searchAllCourse();
        Assert.assertEquals(response.statusCode(), 200);
        List<CourseResponse> allCourses = response.jsonPath().getList(".", CourseResponse.class);
        Assert.assertTrue(allCourses.size() > 0);
        for (CourseResponse course : allCourses) {
            Assert.assertNotNull(course.getCourseCode());
            Assert.assertNotNull(course.getTitle());
            Assert.assertNotNull(course.get_id());
            Assert.assertTrue(course.getAvailableSlots() >= 0);
        }

        //Search Course by Title
        response = courseService.searchCourseByTitle(title);
        Assert.assertEquals(response.statusCode(), 200);
        List<CourseResponse> searchResult = response.jsonPath().getList(".", CourseResponse.class);
        CourseResponse first = searchResult.get(0);
        Assert.assertEquals(first.get_id(), courseId);
        Assert.assertEquals(first.getCourseCode(), courseCode);
        Assert.assertEquals(first.getTitle(), title);
    }
}