package com.employeepayroll.service;


import com.employeepayroll.dto.EmployeeDTO;
import com.employeepayroll.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    List<Employee> employeeList = new ArrayList<>();
    int idCount = 1;

    public List<EmployeeDTO> getAllEmployees() {
        return employeeList.stream()
                .map(emp -> new EmployeeDTO(emp.getId(), emp.getName(), emp.getSalary()))
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Optional<Employee> employee = employeeList.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst();

        if (employee.isEmpty()) {
            throw new RuntimeException("Employee not found with ID: " + id);
        }

        Employee emp = employee.get();
        return new EmployeeDTO(emp.getId(), emp.getName(), emp.getSalary());
    }

    public String addEmployee(EmployeeDTO employeeDTO) {
        Employee newEmployee = new Employee(idCount, employeeDTO.getName(), employeeDTO.getSalary());
        employeeList.add(newEmployee);
        idCount++;
        return "Employee added successfully!";
    }

    public String updateEmployee(Long id, EmployeeDTO employeeDTO) {
        for (Employee emp : employeeList) {
            if (emp.getId().equals(id)) {
                emp.setName(employeeDTO.getName());
                emp.setSalary(employeeDTO.getSalary());
                return "Employee updated successfully!";
            }
        }
        return "Employee not found!";
    }

    public String deleteEmployee(Long id) {
        boolean removed = employeeList.removeIf(emp -> emp.getId().equals(id));
        return removed ? "Employee deleted successfully!" : "Employee not found!";
    }
}
