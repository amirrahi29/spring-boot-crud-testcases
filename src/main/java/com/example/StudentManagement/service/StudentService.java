package com.example.StudentManagement.service;

import com.example.StudentManagement.response.BasicResponseMsg;
import com.example.StudentManagement.response.StudentRequest;


public interface StudentService {

    BasicResponseMsg saveStudent(StudentRequest studentRequest);

    BasicResponseMsg getStudentById(StudentRequest studentRequest);

    BasicResponseMsg getAllStudents();

    BasicResponseMsg updateStudent(StudentRequest studentRequest);

    BasicResponseMsg deleteStudentById(StudentRequest studentRequest);
    
}
