package com.example.TourProject.Dto.TourDetail;


import lombok.Data;

import java.time.LocalDate;
@Data
public class TourDetailRequestDto {

    private Long tourId;
    private Long touristId;
    private Long employeeId;
    private LocalDate tourDate;

}
