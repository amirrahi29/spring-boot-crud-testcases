package com.example.StudentManagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.StudentManagement.response.BasicResponseMsg;
import com.example.StudentManagement.response.StudentRequest;
import com.example.StudentManagement.service.StudentService;

@RestController
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("saveStudent")
    public ResponseEntity<BasicResponseMsg> saveStudent(@RequestBody StudentRequest studentRequest) {
         BasicResponseMsg response = new BasicResponseMsg();
         try {
            response = studentService.saveStudent(studentRequest);     
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("getStudentById")
    public ResponseEntity<BasicResponseMsg> getStudentById(@RequestBody StudentRequest studentRequest) {
         BasicResponseMsg response = new BasicResponseMsg();
         try {
            response = studentService.getStudentById(studentRequest);     
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("getAllStudents")
    public ResponseEntity<BasicResponseMsg> getAllStudents() {
         BasicResponseMsg response = new BasicResponseMsg();
         try {
            response = studentService.getAllStudents();     
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("updateStudent")
    public ResponseEntity<BasicResponseMsg> updateStudent(@RequestBody StudentRequest studentRequest) {
         BasicResponseMsg response = new BasicResponseMsg();
         try {
            response = studentService.updateStudent(studentRequest);     
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("deleteStudentById")
    public ResponseEntity<BasicResponseMsg> deleteStudentById(@RequestBody StudentRequest studentRequest) {
         BasicResponseMsg response = new BasicResponseMsg();
         try {
            response = studentService.deleteStudentById(studentRequest);     
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
