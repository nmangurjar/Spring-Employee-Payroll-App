package com.employeepayroll.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class EmployeeDTO {
    private Long id;
    private String name;
    private double salary;

    public EmployeeDTO() {}

    public EmployeeDTO(Long id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

}