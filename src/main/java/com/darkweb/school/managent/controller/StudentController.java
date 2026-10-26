package com.darkweb.school.managent.controller;

import com.darkweb.school.managent.model.StudentData;
import com.darkweb.school.managent.service.StudentServices;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {
    @Autowired
    StudentServices services;

//    @GetMapping("/csrf")
//    public CsrfToken getToken(HttpServletRequest request){
//        return (CsrfToken) request.getAttribute("_csrf");
//    }

    @GetMapping("/")
    public String Hello(HttpServletRequest request){
        return "hello welcome to springboot" + request.getSession().getId();
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentData>> getAllPupils(){
        List<StudentData> myPupil = services.getAllPupil();
        if(myPupil.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(myPupil , HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentData>getByID(@PathVariable int id){
        StudentData data = services.getpupilByID(id);
        if(data == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(data , HttpStatus.OK);
        }
    }

    //add student using http://localhost:8090/api/v1/students

    @PostMapping("/students")
    public void studentadded(@RequestBody StudentData data){
       services.addStundent(data);
    }

    //Edit the student request: http://localhost:8090/api/v1/students/{id}
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentData> updateStudents(@PathVariable int id , @RequestBody StudentData data){
         return new ResponseEntity<>(services.updateDetails(id , data) , HttpStatus.OK);
    }

    //delete student using http://localhost:8090/api/v1/students/{id}
    @DeleteMapping("/students/{id}")

   public void deleteStudent(@PathVariable int id){
         services.removeStudent(id);
    }
}
