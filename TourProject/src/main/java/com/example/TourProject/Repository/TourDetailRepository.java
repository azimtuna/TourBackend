package com.example.TourProject.Repository;

import com.example.TourProject.Model.TourDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourDetailRepository extends JpaRepository <TourDetail,Long>{
}
