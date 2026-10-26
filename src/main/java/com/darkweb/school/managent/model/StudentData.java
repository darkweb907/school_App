package com.darkweb.school.managent.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
public class StudentData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private int age;
    private  String name;
    
    @Column(nullable = false ,unique = true)
    private  String email;
    private  int enrollClass;

}
