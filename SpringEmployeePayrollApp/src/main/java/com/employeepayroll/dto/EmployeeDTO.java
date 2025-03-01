package com.employeepayroll.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class EmployeeDTO {
    private Long id;
    @NotEmpty(message = "Name cannot be Empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z]{2,}$", message = "Name should start with Capital letter and have minimum 3 letters")
    private String name;
    private double salary;
    private String gender;
    private Date startDate;
    private String note;
    private String profilePic;
    private String department;

}