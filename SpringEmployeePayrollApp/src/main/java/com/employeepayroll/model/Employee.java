package com.employeepayroll.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double salary;

    public Employee() {}

    public Employee(Long id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}
