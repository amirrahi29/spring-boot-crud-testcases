package com.example.StudentManagement.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.StudentManagement.dao.StudentDao;
import com.example.StudentManagement.model.StudentModel;
import com.example.StudentManagement.response.BasicResponseMsg;
import com.example.StudentManagement.response.StudentRequest;
import com.example.StudentManagement.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

     public StudentServiceImpl(StudentDao studentDao) {
         this.studentDao = studentDao;
     }

    @Override
    public BasicResponseMsg saveStudent(StudentRequest studentRequest) {
        
        BasicResponseMsg responseMsg = new BasicResponseMsg();

        try {
            StudentModel s = new StudentModel();
            s.setName(studentRequest.getName());
            s.setEmail(studentRequest.getEmail());
            s.setPassword(studentRequest.getPassword());
            StudentModel savedStudent = studentDao.save(s);

            //setting data on base response model to display users of their response
            responseMsg.setData(savedStudent.getStudentId());
            // responseMsg.setData(savedStudent);   //for display all data
            responseMsg.setMessage("Student Saved");
            responseMsg.setStatus(200);
            logger.info(studentRequest.getName()," is added into students list: {}",responseMsg);

        } catch (Exception e) {
            e.printStackTrace();
             //setting data on base response model to display users of their response
            responseMsg.setData(null);
            responseMsg.setMessage("Student Addition Failed!"+e.getMessage());
            responseMsg.setStatus(300);
            logger.info("Student Addition Failed! {}",e.getMessage());
        }
        return responseMsg;
    }

    @Override
    public BasicResponseMsg getStudentById(StudentRequest studentRequest) {

        BasicResponseMsg responseMsg = new BasicResponseMsg();
        try {
           Optional<StudentModel> findByIdListStudent = studentDao.findById(Long.valueOf(studentRequest.getId()));
           if(findByIdListStudent.isPresent()){
               StudentModel studentModelData = findByIdListStudent.get();
               responseMsg.setStatus(200);
               responseMsg.setMessage("Data found");
               responseMsg.setData(studentModelData);
               logger.info("allStudentsList: {}",responseMsg);
           }else{
               responseMsg.setStatus(300);
               responseMsg.setMessage("Data not found");
               responseMsg.setData(null);
           }
        } catch (Exception e) {
            e.printStackTrace();
               responseMsg.setStatus(400);
               responseMsg.setMessage("Data not found "+e.getMessage());
               responseMsg.setData(null);
            logger.info("No students available!: {}",e.getMessage());
        }
        return responseMsg;
    }

    @Override
    public BasicResponseMsg getAllStudents() {
       BasicResponseMsg responseMsg = new BasicResponseMsg();
        try {
           List<StudentModel> allStudents = studentDao.findAll();
           if(!allStudents.isEmpty()){
               responseMsg.setStatus(200);
               responseMsg.setMessage("Data found");
               responseMsg.setData(allStudents);
               logger.info("allStudentsList: {}",responseMsg);
           }else{
               responseMsg.setStatus(300);
               responseMsg.setMessage("Data not found");
               responseMsg.setData(null);
               logger.info("No students available!");
           }
        } catch (Exception e) {
            e.printStackTrace();
               responseMsg.setStatus(400);
               responseMsg.setMessage("Data not found "+e.getMessage());
               responseMsg.setData(null);
            logger.info("No students available!: {}",e.getMessage());
        }
        return responseMsg;
    }

    @Override
    public BasicResponseMsg updateStudent(StudentRequest studentRequest) {
        BasicResponseMsg responseMsg = new BasicResponseMsg();

        try {
            Optional<StudentModel> checkStudentAvailability = studentDao.findById(Long.valueOf(studentRequest.getId()));
            if(checkStudentAvailability.isPresent()){
                StudentModel studentModel = checkStudentAvailability.get();
                studentModel.setName(studentRequest.getName());
                studentModel.setEmail(studentRequest.getEmail());
                studentModel.setPassword(studentModel.getPassword());
                studentDao.save(studentModel);

                    //setting data on base response model to display users of their response
                    responseMsg.setData(studentModel.getStudentId());
                    // responseMsg.setData(savedStudent);   //for display all data
                    responseMsg.setMessage("Student Updated");
                    responseMsg.setStatus(200);
                     logger.info("Student updated: {}",responseMsg);
            }else{
                responseMsg.setStatus(300);
                responseMsg.setMessage("Student not found");
                responseMsg.setData(null);
                logger.info("Student not available!: ");
            }
        } catch (Exception e) {
            e.printStackTrace();
             //setting data on base response model to display users of their response
            responseMsg.setData(null);
            responseMsg.setMessage("Student Updation Failed!"+e.getMessage());
            responseMsg.setStatus(300);
            logger.info("Student Updation Failed!: {}",e.getMessage());

        }
        return responseMsg;
    }

    @Override
    public BasicResponseMsg deleteStudentById(StudentRequest studentRequest) {
        BasicResponseMsg responseMsg = new BasicResponseMsg();
        try {
           Optional<StudentModel> findByIdDelStudent = studentDao.findById(Long.valueOf(studentRequest.getId()));
           if(findByIdDelStudent.isPresent()){
               StudentModel studentModelData = findByIdDelStudent.get();
               studentDao.delete(studentModelData);
               responseMsg.setStatus(200);
               responseMsg.setMessage("Student deleted");
               responseMsg.setData(studentModelData);
               logger.info("Student deleted: {}",responseMsg);
           }else{
               responseMsg.setStatus(300);
               responseMsg.setMessage("No student found!");
               responseMsg.setData(null);
               logger.info("Student not found!: ");
           }
        } catch (Exception e) {
            e.printStackTrace();
               responseMsg.setStatus(400);
               responseMsg.setMessage("Student not found "+e.getMessage());
               responseMsg.setData(null);
            logger.info("Student not found: {}",e.getMessage());
        }
        return responseMsg;
    }
    
}
