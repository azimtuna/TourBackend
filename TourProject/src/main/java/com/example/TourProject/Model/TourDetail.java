package com.example.TourProject.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TourDetails")
public class TourDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_detail_id")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "tour_id")
    private Tour tour;
    @ManyToOne()
    @JoinColumn(name = "tourist_id")
    private Tourist tourist;
    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Column(name ="tour_date")
    private LocalDate tourDate;

}

