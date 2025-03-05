package com.example.StudentManagement.Controller;

import com.example.StudentManagement.controller.StudentController;
import com.example.StudentManagement.response.BasicResponseMsg;
import com.example.StudentManagement.response.StudentRequest;
import com.example.StudentManagement.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean  // Use @MockBean instead of @Mock to allow Spring to recognize it as a bean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveStudent() throws Exception {
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setId("1");
        studentRequest.setName("John Doe");
        studentRequest.setEmail("john@example.com");
        studentRequest.setPassword("password123");

        BasicResponseMsg responseMsg = new BasicResponseMsg();
        responseMsg.setStatus(200);
        responseMsg.setMessage("Student Saved");
        responseMsg.setData("1");

        when(studentService.saveStudent(any(StudentRequest.class))).thenReturn(responseMsg);

        mockMvc.perform(post("/saveStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))  // Ensure "status" exists
                .andExpect(jsonPath("$.message").value("Student Saved")) // Ensure "message" exists
                .andExpect(jsonPath("$.data").value("1"));  // Ensure "data" exists
    }

    @Test
    void testGetStudentById() throws Exception {
        StudentRequest request = new StudentRequest();
        request.setId("1");

        BasicResponseMsg responseMsg = new BasicResponseMsg();
        responseMsg.setStatus(200);
        responseMsg.setMessage("Data found");
        responseMsg.setData(request);

        when(studentService.getStudentById(any(StudentRequest.class))).thenReturn(responseMsg);

        mockMvc.perform(get("/getStudentById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Data found"))
                .andExpect(jsonPath("$.data.id").value("1"));
    }

    @Test
    void testGetAllStudents() throws Exception {
        BasicResponseMsg responseMsg = new BasicResponseMsg();
        responseMsg.setStatus(200);
        responseMsg.setMessage("Data found");
        responseMsg.setData(Arrays.asList("Student1", "Student2"));

        when(studentService.getAllStudents()).thenReturn(responseMsg);

        mockMvc.perform(get("/getAllStudents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Data found"))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void testUpdateStudent() throws Exception {
        // Create test request with correct String ID
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setId("1"); // Fixed: String ID instead of "1L"
        studentRequest.setName("Updated Name");
        studentRequest.setEmail("updated@example.com");

        // Create mock response with String ID
        BasicResponseMsg responseMsg = new BasicResponseMsg();
        responseMsg.setStatus(200);
        responseMsg.setMessage("Student Updated");
        responseMsg.setData("1"); // Fixed: String instead of Long

        // Mock service response
        when(studentService.updateStudent(any(StudentRequest.class))).thenReturn(responseMsg);

        // Perform API test with correct expectations
        mockMvc.perform(post("/updateStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Student Updated"))
                .andExpect(jsonPath("$.data").value("1")); // Fixed: String instead of Long
    }


    @Test
    void testDeleteStudentById() throws Exception {
        // Create test request with correct String ID
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setId("1");

        // Create mock response with String ID
        BasicResponseMsg responseMsg = new BasicResponseMsg();
        responseMsg.setStatus(200);
        responseMsg.setMessage("Student deleted");
        responseMsg.setData("1"); // Fixed: String instead of Integer

        // Mock service response correctly
        when(studentService.deleteStudentById(any(StudentRequest.class))).thenReturn(responseMsg);

        // Perform API test with correct expectations
        mockMvc.perform(post("/deleteStudentById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Student deleted"))
                .andExpect(jsonPath("$.data").value("1")); // Fixed: Expecting String "1" instead of Integer 1
    }

}
