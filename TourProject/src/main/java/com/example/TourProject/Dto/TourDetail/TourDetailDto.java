package com.example.TourProject.Dto.TourDetail;

import com.example.TourProject.Dto.Employee.EmployeeDto;
import com.example.TourProject.Dto.Tour.TourDto;
import com.example.TourProject.Dto.Tourist.TouristDto;
import lombok.Data;

import java.time.LocalDate;
@Data
public class TourDetailDto {
    private Long id;
    private TourDto tour;
    private TouristDto tourist;
    private EmployeeDto employee;
    private LocalDate tourDate;
}
