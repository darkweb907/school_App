package com.darkweb.school.managent.service;

import com.darkweb.school.managent.model.StudentData;
import com.darkweb.school.managent.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServices {
    @Autowired
     Repo repo;




    public List<StudentData> getAllPupil() {
        return repo.findAll();
    }

    public StudentData getpupilByID(int id) {
        return repo.findById(id).orElse(null);
    }

    public void addStundent(StudentData data) {
        repo.save(data);
    }

    public StudentData updateDetails(int id, StudentData data) {

        StudentData existingStudent = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with ID: " + id));

        existingStudent.setName(data.getName());
        existingStudent.setAge(data.getAge());
        existingStudent.setEmail(data.getEmail());
        existingStudent.setEnrollClass(data.getEnrollClass());

        return repo.save(existingStudent);
    }

    public void removeStudent(int id) {
        repo.deleteById(id);
    }
}
