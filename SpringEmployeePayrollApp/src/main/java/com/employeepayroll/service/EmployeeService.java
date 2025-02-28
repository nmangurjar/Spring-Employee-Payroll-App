package com.employeepayroll.service;


import com.employeepayroll.dto.EmployeeDTO;
import com.employeepayroll.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
public class EmployeeService {
    List<Employee> employeeList = new ArrayList<>();
    int idCount = 1;

    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching All Employees");
        return employeeList.stream()
                .map(emp -> new EmployeeDTO(emp.getId(), emp.getName(), emp.getSalary()))
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        log.debug("Fetching Employee with ID: {}", id);
        Optional<Employee> employee = employeeList.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst();

        if (employee.isEmpty()) {
            log.error("Employee Not Found with ID: {}", id);
            throw new RuntimeException("Employee not found with ID: " + id);
        }

        return new EmployeeDTO(employee.get().getId(), employee.get().getName(), employee.get().getSalary());
    }

    public String addEmployee(EmployeeDTO employeeDTO) {
        log.info("Adding Employee: {}", employeeDTO.getName());
        Employee newEmployee = new Employee(idCount, employeeDTO.getName(), employeeDTO.getSalary());
        employeeList.add(newEmployee);
        idCount++;
        return "Employee added successfully!";
    }
    public String updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating Employee: {}", employeeDTO.getName());
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
        log.info("Deleting Employee with ID: {}", id);
        boolean removed = employeeList.removeIf(emp -> emp.getId().equals(id));
        return removed ? "Employee deleted successfully!" : "Employee not found!";
    }
}
