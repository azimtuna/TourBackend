package com.example.TourProject.Dto.Tourist;

import com.example.TourProject.Model.TourDetail;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class TouristDto {
    private long touristId;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String nationality;
}
