package com.geekster.SpringAnnotations.Controller;

import com.geekster.SpringAnnotations.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    Student s1;

    @GetMapping("/student")
    public Student getStudent()
    {
        return s1;
    }
}
