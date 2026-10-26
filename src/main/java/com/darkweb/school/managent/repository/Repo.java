package com.darkweb.school.managent.repository;

import com.darkweb.school.managent.model.StudentData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<StudentData , Integer> {
}
