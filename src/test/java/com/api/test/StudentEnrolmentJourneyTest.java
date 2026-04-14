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
    public void studentCourseEnrolmentTest() {

        LoginService loginService = new LoginService();
        CourseService courseService = new CourseService();
        EnrolmentService enrolmentService = new EnrolmentService();
        Response response;

        //Student Login
        LoginRequest loginRequest = new LoginRequest(ConfigReader.get("STUDENT_USERNAME"), ConfigReader.get("PASSWORD"));
        response = loginService.studentLogin(loginRequest);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.time() < 30000, "Login took too long");
        LoginResponse loginResponse = response.as(LoginResponse.class);
        String token = loginResponse.getToken();
        Assert.assertNotNull(token);

        //Search Course by title
        response = courseService.searchCourseByTitle(ConfigReader.get("COURSE_TITLE"));
        Assert.assertEquals(response.statusCode(), 200);
        List<CourseResponse> courses = response.jsonPath().getList(".", CourseResponse.class);
        CourseResponse firstCourse = courses.get(0);
        String courseCode = firstCourse.getCourseCode();
        Assert.assertNotNull(courseCode);
        Assert.assertTrue(firstCourse.getTitle().contains(ConfigReader.get("COURSE_TITLE")));

        //Check course slot - BEFORE
        response = courseService.checkCourseSlot(courseCode);
        Assert.assertEquals(response.statusCode(), 200);
        CourseAvailabilityResponse availabilityBefore = response.as(CourseAvailabilityResponse.class);
        int availableSlots = availabilityBefore.getAvailableSlots();

        //Enrol student using course code
        EnrolmentRequest enrolRequest = new EnrolmentRequest(ConfigReader.get("STUDENT_USERNAME"), courseCode);
        response = enrolmentService.courseEnrolment(token, enrolRequest);
        Assert.assertEquals(response.statusCode(), 201);
        EnrolementResponse enrolResponse = response.as(EnrolementResponse.class);
        Assert.assertEquals(enrolResponse.getMessage(), "Enrolled successfully");

        //Verify Enrolment
        ActiveCourseEnrolmentRequest activeRequest = new ActiveCourseEnrolmentRequest(ConfigReader.get("STUDENT_USERNAME"));
        response = enrolmentService.activeCourseEnrolment(token, activeRequest);
        Assert.assertEquals(response.statusCode(), 200);
        List<ActiveCourseEnrolmentResponse> activeList = response.jsonPath().getList(".", ActiveCourseEnrolmentResponse.class);
        Assert.assertTrue(activeList.size() > 0);

        //Slot validation
        response = courseService.checkCourseSlot(courseCode);
        CourseAvailabilityResponse availabilityAfterEnrol = response.as(CourseAvailabilityResponse.class);
        Assert.assertEquals(availabilityAfterEnrol.getAvailableSlots(), availableSlots - 1);

        //Drop a course
        response = enrolmentService.dropCourse(token, enrolRequest);
        Assert.assertEquals(response.statusCode(), 200);
        EnrolementResponse dropResponse = response.as(EnrolementResponse.class);
        Assert.assertEquals(dropResponse.getMessage(), "Course dropped successfully");

        //Verify Active Enrolment
        response = enrolmentService.activeCourseEnrolment(token, activeRequest);
        Assert.assertEquals(response.statusCode(), 200);
        List<ActiveCourseEnrolmentResponse> afterDropList = response.jsonPath().getList(".", ActiveCourseEnrolmentResponse.class);
        Assert.assertTrue(afterDropList == null || afterDropList.isEmpty(), "Expected no active enrolments");

        //Verify slot restored
        response = courseService.checkCourseSlot(courseCode);
        CourseAvailabilityResponse finalSlot = response.as(CourseAvailabilityResponse.class);
        Assert.assertEquals(finalSlot.getAvailableSlots(), availableSlots);
    }
}