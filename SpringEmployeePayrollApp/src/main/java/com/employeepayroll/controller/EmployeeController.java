package com.employeepayroll.controller;




import com.employeepayroll.model.Employee;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.employeepayroll.dto.EmployeeDTO;


import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final List<Employee> employeeList = new ArrayList<>();

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee emp : employeeList) {
            employeeDTOs.add(new EmployeeDTO(emp.getName(), emp.getSalary()));
        }
        return employeeDTOs;
    }

    @GetMapping("/get/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeList.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst();

        if (employee.isEmpty()) {
            throw new RuntimeException("Employee not found with ID: " + id);
        }

        Employee emp = employee.get();
        return new EmployeeDTO(emp.getName(), emp.getSalary());
    }

    @PostMapping
    public String addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee newEmployee = new Employee(employeeDTO.getName(), employeeDTO.getSalary());
        employeeList.add(newEmployee);
        return "Employee added successfully!";
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        boolean removed = employeeList.removeIf(emp -> emp.getId().equals(id));
        return removed ? "Employee deleted successfully!" : "Employee not found!";
    }
    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        for (Employee emp : employeeList) {
            if (emp.getId().equals(id)) {
                emp.setName(employeeDTO.getName());
                emp.setSalary(employeeDTO.getSalary());
                return "Employee updated successfully!";
            }
        }
        return "Employee not found!";
    }
}