package com.api.test;

import com.api.base.CourseService;
import com.api.base.LoginService;
import com.api.models.request.AddNewCourseRequest;
import com.api.models.request.LoginRequest;
import com.api.models.response.AddNewCourseResponse;
import com.api.models.response.CourseResponse;
import com.api.models.response.LoginResponse;
import com.api.utils.ApiArrayValidator;
import com.api.utils.ConfigReader;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CourseJourneyTest {

    LocalDate startDate = LocalDate.now().withDayOfMonth(1);
    LocalDate endLocalDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

    @Test(description = "create, delete, search all, search by title, and check course availability")
    public void courseJourneyTest() throws ParseException {
        //Student logs in to gets token
        LoginRequest loginRequest = new LoginRequest(ConfigReader.get("instructor_username"), ConfigReader.get("password"));
        LoginService loginService = new LoginService();
        Response response = loginService.instructorLogin(loginRequest);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        String token = loginResponse.getToken();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(loginResponse.getToken());
        Assert.assertTrue(response.time() < 30000, "Response took more than 30 seconds");

        //Add a new course to the catalog
        CourseService courseService = new CourseService();
        Date courseStartDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date courseEndDate = Date.from(endLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String courseCode = "AWSCloud_" + UUID.randomUUID().toString().substring(0, 6);
        String title = "AWS Cloud Course " + (int)(Math.random() * 10000);
        String instructor = "instructor" + (int)(Math.random() * 10000);
        AddNewCourseRequest addNewCourseRequest = new AddNewCourseRequest.Builder().title(title)
                .instructor(instructor)
                .courseCode(courseCode)
                .category("Cloud")
                .totalCapacity(30)
                .startDate(courseStartDate)
                .endDate(courseEndDate)
                .build();
        response = courseService.addNewCourse(token, addNewCourseRequest);
        Assert.assertEquals(response.statusCode(), 201);
        AddNewCourseResponse addNewCourseResponse = response.as(AddNewCourseResponse.class);
        String courseId = addNewCourseResponse.getNewCourse().get_id();
        Assert.assertEquals(addNewCourseResponse.getMessage(), "Course added successfully");
        Assert.assertEquals(addNewCourseResponse.getNewCourse().getTitle(), title);
        Assert.assertEquals(addNewCourseResponse.getNewCourse().getInstructor(), instructor);
        Assert.assertEquals(addNewCourseResponse.getNewCourse().getCourseCode(), courseCode);
        Assert.assertEquals(addNewCourseResponse.getNewCourse().getCategory(), "Cloud");
        Assert.assertEquals(addNewCourseResponse.getNewCourse().getTotalCapacity(), 30);
        Assert.assertEquals(addNewCourseResponse.getNewCourse().getStartDate(), courseStartDate);
        Assert.assertEquals(addNewCourseResponse.getNewCourse().getEndDate(), courseEndDate);
        Assert.assertTrue(response.time() < 30000, "Response took more than 30 seconds");

        //Search All Courses
        response = courseService.searchAllCourse();
        Assert.assertEquals(response.statusCode(), 200);
        List<Map<String, Object>> courses = new ApiArrayValidator(response).asList();
        for (Map<String, Object> courseList : courses) {
            Assert.assertNotNull(courseList.get("courseCode"));
            Assert.assertNotNull(courseList.get("title"));
            Assert.assertNotNull(courseList.get("_id"));
            Assert.assertTrue((int) courseList.get("availableSlots") >= 0);
        }

        //Search By Title
        response = courseService.searchCourseByTitle(title);
        Assert.assertEquals(response.statusCode(), 200);
        List<CourseResponse> courseList = response.jsonPath().getList(".", CourseResponse.class);
        CourseResponse firstCourse = courseList.get(0);
        Assert.assertEquals(firstCourse.get_id(), courseId);
        Assert.assertEquals(firstCourse.getCourseCode(), courseCode);
        Assert.assertEquals(firstCourse.getTitle(), title);
        Assert.assertTrue(firstCourse.getTotalCapacity()>0);
        Assert.assertTrue(firstCourse.getAvailableSlots()>0);
        Assert.assertTrue(response.time() < 30000, "Response took more than 30 seconds");

    }

}
