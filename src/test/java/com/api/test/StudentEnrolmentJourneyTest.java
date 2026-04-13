package com.api.test;

import com.api.base.CourseService;
import com.api.base.EnrolmentService;
import com.api.base.LoginService;
import com.api.models.request.ActiveCourseEnrolmentRequest;
import com.api.models.request.EnrolmentRequest;
import com.api.models.request.LoginRequest;
import com.api.models.response.*;
import com.api.utils.ConfigReader;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class StudentEnrolmentJourneyTest {

    @Test(description = "Student course enrollment end-to-end flow")
    public void studentCourseEnrolmentTest(){
        //Student logs in to gets token
        LoginRequest loginRequest = new LoginRequest(ConfigReader.get("student_username"), ConfigReader.get("password"));
        LoginService loginService = new LoginService();
        Response response = loginService.studentLogin(loginRequest);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        String token = loginResponse.getToken();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(loginResponse.getToken());
        Assert.assertTrue(response.time() < 30000, "Response took more than 30 seconds");

        //Search for a course
        CourseService courseService = new CourseService();
        response = courseService.searchCourseByTitle(ConfigReader.get("course_title"));
        Assert.assertEquals(response.statusCode(), 200);
        List<CourseResponse> courses = response.jsonPath().getList(".", CourseResponse.class);
        CourseResponse firstCourse = courses.get(0);
        String courseCode = firstCourse.getCourseCode();
        Assert.assertNotNull(courseCode);
        Assert.assertNotNull(firstCourse.get_id());
        Assert.assertTrue(firstCourse.getTitle().contains(ConfigReader.get("course_title")));
        Assert.assertNotNull(firstCourse.getInstructor());
        Assert.assertNotNull(firstCourse.getCategory());
        Assert.assertNotNull(firstCourse.getStartDate());
        Assert.assertNotNull(firstCourse.getEndDate());
        Assert.assertTrue(firstCourse.getTotalCapacity()>0);
        Assert.assertTrue(firstCourse.getAvailableSlots()>0);
        Assert.assertTrue(response.time() < 30000, "Response took more than 30 seconds");

        //Check course availability
        response = courseService.checkCourseSlot(courseCode);
        Assert.assertEquals(response.statusCode(), 200);
        CourseAvailabilityResponse courseAvailabilityResponse = response.as(CourseAvailabilityResponse.class);
        int availableSlot = courseAvailabilityResponse.getAvailableSlots();
        Assert.assertEquals(courseAvailabilityResponse.getCourseCode(), courseCode);
        Assert.assertTrue(courseAvailabilityResponse.getAvailableSlots()>0);
        Assert.assertEquals(courseAvailabilityResponse.getTitle(), firstCourse.getTitle());

        //Course enrollment
        EnrolmentService enrolmentService = new EnrolmentService();
        EnrolmentRequest enrolmentRequest = new EnrolmentRequest(ConfigReader.get("student_username"), courseCode);
        response = enrolmentService.courseEnrolment(token, enrolmentRequest);
        Assert.assertEquals(response.statusCode(), 201);
        EnrolementResponse enrolementResponse = response.as(EnrolementResponse.class);
        Assert.assertEquals(enrolementResponse.getMessage(), "Enrolled successfully");
        Assert.assertEquals(enrolementResponse.getEnrolmentJson().getUsername(), ConfigReader.get("student_username"));
        Assert.assertEquals(enrolementResponse.getEnrolmentJson().getCourseCode(), courseCode);
        Assert.assertEquals(enrolementResponse.getEnrolmentJson().getStatus(), "active");

        //Verify enrolment history
        EnrolmentService activeEnrolmentService = new EnrolmentService();
        ActiveCourseEnrolmentRequest activeCourseEnrolmentRequest = new ActiveCourseEnrolmentRequest(ConfigReader.get("student_username"));
        response = activeEnrolmentService.activeCourseEnrolment(token, activeCourseEnrolmentRequest);
        Assert.assertEquals(response.statusCode(), 200);
        List<ActiveCourseEnrolmentResponse> activeCourseList = response.jsonPath().getList(".", ActiveCourseEnrolmentResponse.class);
        ActiveCourseEnrolmentResponse activeCourse = activeCourseList.get(0);
        Assert.assertEquals(activeCourse.getUsername(), ConfigReader.get("student_username"));
        Assert.assertEquals(activeCourse.getStatus(), "active");

        //Verify course available slot
        response = courseService.checkCourseSlot(courseCode);
        Assert.assertEquals(response.statusCode(), 200);
        courseAvailabilityResponse = response.as(CourseAvailabilityResponse.class);
        Assert.assertEquals(courseAvailabilityResponse.getAvailableSlots(), availableSlot-1);

        //Drop course
        EnrolmentService dropEnrolmentService = new EnrolmentService();
        EnrolmentRequest dropCourseRequest = new EnrolmentRequest(ConfigReader.get("student_username"), courseCode);
        response = dropEnrolmentService.dropCourse(token, dropCourseRequest);
        Assert.assertEquals(response.statusCode(), 200);
        EnrolementResponse dropCourseResponse = response.as(EnrolementResponse.class);
        Assert.assertEquals(dropCourseResponse.getMessage(), "Course dropped successfully");
        Assert.assertEquals(dropCourseResponse.getEnrolmentJson().getCourseCode(), courseCode);
        Assert.assertEquals(dropCourseResponse.getEnrolmentJson().getStatus(), "dropped");

        //Verify course removed
        EnrolmentService verifyCourseRemoval = new EnrolmentService();
        ActiveCourseEnrolmentRequest verifyEnrolmentRequest = new ActiveCourseEnrolmentRequest(ConfigReader.get("student_username"));
        response = verifyCourseRemoval.activeCourseEnrolment(token, verifyEnrolmentRequest);
        Assert.assertEquals(response.statusCode(), 200);
        List<ActiveCourseEnrolmentResponse> list = response.jsonPath().getList(".", ActiveCourseEnrolmentResponse.class);
        Assert.assertTrue(list == null || list.isEmpty(), "Expected no active enrolments");

        //Verify course available slot
        response = courseService.checkCourseSlot(courseCode);
        Assert.assertEquals(response.statusCode(), 200);
        courseAvailabilityResponse = response.as(CourseAvailabilityResponse.class);
        Assert.assertEquals(courseAvailabilityResponse.getAvailableSlots(), availableSlot);

    }
}
