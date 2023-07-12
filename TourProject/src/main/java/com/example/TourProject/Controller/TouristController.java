package com.example.TourProject.Controller;

import com.example.TourProject.Dto.Tourist.TouristDto;
import com.example.TourProject.Dto.Tourist.TouristRequestDto;
import com.example.TourProject.Service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tourist")
public class TouristController {
    private final TouristService touristService;


    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("/getAll")
    public List<TouristDto> getAllTourist(){
        return touristService.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<TouristDto> createTourist(TouristRequestDto touristRequestDto){
        return new ResponseEntity<>(touristService.createTourist(touristRequestDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTourist(Long id){
        touristService.deleteTourist(id);
        return ResponseEntity.noContent().build();
    }

}
