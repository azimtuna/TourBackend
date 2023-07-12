package com.example.TourProject.Controller;

import com.example.TourProject.Dto.Tour.TourDto;
import com.example.TourProject.Dto.Tour.TourRequestDto;
import com.example.TourProject.Service.TourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/tour")
@RestController
public class TourController {
    private final TourService tourService;


    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TourDto>> getAll(){
        return ResponseEntity.ok(tourService.getAll());
    }

    @PostMapping("/createTour")
    public ResponseEntity<TourDto> createTour(@RequestBody TourRequestDto tourRequestDto){
        return new ResponseEntity<>(tourService.createTour(tourRequestDto),HttpStatus.CREATED);
    }

    @PutMapping("/updateTour/{id}")
    public ResponseEntity<TourDto> updateTour(@PathVariable Long id,@RequestBody TourRequestDto tourRequestDto){
        return new ResponseEntity<>(tourService.updateTour(id, tourRequestDto),HttpStatus.OK);
    }
    @GetMapping("/getOneTour")
    public ResponseEntity<TourDto> getOneTour(Long id){
        return new ResponseEntity<>(tourService.getOneTour(id), HttpStatus.OK);
    }
    @DeleteMapping("/deleteTour/{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable Long id){
        tourService.deleteTour(id);
        return ResponseEntity.noContent().build();
    }




}
