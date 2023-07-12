package com.example.TourProject.Repository;

import com.example.TourProject.Model.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristRepository extends JpaRepository<Tourist,Long> {


}
