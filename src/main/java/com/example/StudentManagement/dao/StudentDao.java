package com.example.StudentManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.StudentManagement.model.StudentModel;

public interface StudentDao extends JpaRepository<StudentModel,Long> {
    
}
