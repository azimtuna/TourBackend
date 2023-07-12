package com.example.TourProject.Dto.Tourist;

import lombok.Data;

import java.time.LocalDate;
@Data
public class TouristRequestDto {
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String nationality;


}
