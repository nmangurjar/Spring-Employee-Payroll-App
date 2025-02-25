package com.employeepayroll.model;

public class Employee {


    private Long id;
    private String name;
    private double salary;

    public Employee() {
    }
    public Employee(long id,String name ,double salary) {
        this.id = id;
        this.salary = salary;
        this.name = name;
    }


    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
}