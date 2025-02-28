package com.employeepayroll.service;


import com.employeepayroll.dto.EmployeeDTO;
import com.employeepayroll.model.Employee;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import com.employeepayroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public String addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());

        employeeRepository.save(employee);
        log.info("Employee Added: {}", employee.getName());
        return "Employee added successfully!";
    }

    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching All Employees");
        return employeeRepository.findAll()
                .stream()
                .map(emp -> new EmployeeDTO(emp.getId(), emp.getName(), emp.getSalary()))
                .collect(Collectors.toList());
    }
    public EmployeeDTO getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            log.warn("Employee not found with ID: {}", id);
            throw new RuntimeException("Employee not found with ID: " + id);
        }
        Employee emp = employee.get();
        log.info("Employee Found: {}", emp.getName());
        return new EmployeeDTO(emp.getId(), emp.getName(), emp.getSalary());
    }
    public String updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee emp = employee.get();
            emp.setName(employeeDTO.getName());
            emp.setSalary(employeeDTO.getSalary());
            employeeRepository.save(emp);
            log.info("Employee updated successfully with ID: {}", id);
            return "Employee updated successfully!";
        }
        log.warn("Employee not found with ID: {}", id);
        return "Employee not found!";
    }

    public String deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            log.info("Employee deleted successfully with ID: {}", id);
            return "Employee deleted successfully!";
        } else {
            log.warn("Employee not found with ID: {}", id);
            return "Employee not found!";
        }
    }


}
