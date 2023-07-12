package com.example.TourProject.Dto.Employee;

import com.example.TourProject.Model.TourDetail;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
}
