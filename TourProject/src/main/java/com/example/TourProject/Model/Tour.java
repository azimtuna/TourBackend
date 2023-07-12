package com.example.TourProject.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Tours")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id")
    private long tourId;
    @Column(name = "tourName")
    private String tourName;
    @Column(name = "toruPrice")
    private Double tourPrice;
    @OneToMany(mappedBy = "tour")
    private List<TourDetail> tourDetails;
}
