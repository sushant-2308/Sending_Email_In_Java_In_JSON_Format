package com.geekster.SpringAnnotations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


public class Student {
   private String name;
   private String number;
   private String designation;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        designation = designation;
    }

    public Student(String name, String number, String designation) {
        this.name = name;
        this.number=number;
        this.designation=designation;
    }
}
