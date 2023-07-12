package com.example.TourProject.Service;

import com.example.TourProject.Dto.Tour.TourRequestDto;
import com.example.TourProject.Exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import com.example.TourProject.Dto.Tour.TourDto;
import com.example.TourProject.Model.Tour;
import com.example.TourProject.Repository.TourRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class TourService {

    private final TourRepository tourRepository;
    private final ModelMapper modelMapper;


    public TourService(TourRepository tourRepository, ModelMapper modelMapper) {
        this.tourRepository = tourRepository;
        this.modelMapper = modelMapper;
    }
    protected Tour getTourById(Long id){
        return tourRepository.findById(id).orElseThrow(() -> new NotFoundException("Tour"));
    }
    public List<TourDto> getAll(){
        List<Tour> tours = tourRepository.findAll();
        return tours.stream().map(tour -> modelMapper.map(tour,TourDto.class)).collect(Collectors.toList());
    }
    public TourDto createTour(TourRequestDto tourRequestDto){
        Tour tour = modelMapper.map(tourRequestDto,Tour.class);
        tour.setTourId(0);
        return modelMapper.map(tourRepository.save(tour),TourDto.class);
    }
    public void deleteTour(long id){
        tourRepository.delete(getTourById(id));
    }
    public TourDto updateTour(Long id,TourRequestDto tourRequestDto){
        Tour tour=getTourById(id);
        tour.setTourName(tourRequestDto.getTourName());
        tour.setTourPrice(tourRequestDto.getTourPrice());
        return modelMapper.map(tourRepository.save(tour),TourDto.class);
    }
    public TourDto getOneTour(Long id){
        return modelMapper.map(getTourById(id),TourDto.class);
    }



}
