package com.employeepayroll.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;

@Data
@Slf4j
public class EmployeeDTO {
    private Long id;

    @NotEmpty(message = "Name cannot be Empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z]{2,}$", message = "Name should start with Capital letter and have minimum 3 letters")
    private String name;

    @Positive(message = "Salary must be positive")
    private double salary;

    @NotEmpty(message = "Gender cannot be empty")
    @Pattern(regexp = "Male|Female", message = "Gender should be either Male or Female")
    private String gender;

    @JsonFormat(pattern = "dd MMM yyyy")
    @PastOrPresent(message = "Start Date should be past or present date")
    private LocalDate startDate;

    @NotBlank(message = "Note cannot be empty")
    private String note;

    @NotBlank(message = "Profile Pic cannot be empty")
    private String profilePic;

    @NotBlank(message = "Department cannot be empty")
    private String department;


    public EmployeeDTO(Long id, String name, double salary, String gender, LocalDate startDate, String note, String profilePic, String department) {
    }
}