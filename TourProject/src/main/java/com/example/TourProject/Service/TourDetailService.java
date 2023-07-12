package com.example.TourProject.Service;

import com.example.TourProject.Dto.TourDetail.TourDetailDto;
import com.example.TourProject.Dto.TourDetail.TourDetailRequestDto;
import com.example.TourProject.Exceptions.NotFoundException;
import com.example.TourProject.Model.TourDetail;
import com.example.TourProject.Repository.TourDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourDetailService {

    private final ModelMapper modelMapper;
    private final TourDetailRepository tourDetailRepository;
    private final TourService tourService;
    private final EmployeeService employeeService;
    private final TouristService touristService;

    public TourDetailService(ModelMapper modelMapper, TourDetailRepository tourDetailRepository, TourService tourService, EmployeeService employeeService, TouristService touristService) {
        this.modelMapper = modelMapper;
        this.tourDetailRepository = tourDetailRepository;
        this.tourService = tourService;
        this.employeeService = employeeService;
        this.touristService = touristService;
    }

    protected TourDetail getTourDetailById(Long id){
        return tourDetailRepository.findById(id).orElseThrow(()->new NotFoundException("Tour detail"));
    }
    public List<TourDetailDto> getAll(){
        List<TourDetail> tourDetails=tourDetailRepository.findAll();
        return tourDetails.stream().map(tourdetail-> modelMapper.map(tourdetail,TourDetailDto.class)).collect(Collectors.toList());
    }

    public TourDetailDto createTourDetail(TourDetailRequestDto tourDetailRequestDto){
        TourDetail tourDetail = modelMapper.map(tourDetailRequestDto,TourDetail.class);

        tourDetail.setId(null);
        tourDetail.setTour(tourService.getTourById(tourDetailRequestDto.getTourId()));
        tourDetail.setEmployee(employeeService.getEmployeeById(tourDetailRequestDto.getEmployeeId()));
        tourDetail.setTourist(touristService.getTouristById((tourDetailRequestDto.getTouristId())));
        return modelMapper.map(tourDetailRepository.save(tourDetail),TourDetailDto.class);
    }
    public TourDetailDto updateTourDetail(Long id, TourDetailRequestDto tourDetailRequestDto){
        TourDetail tourDetail=getTourDetailById(id);
        tourDetail.setTour(tourService.getTourById(tourDetailRequestDto.getTourId()));
        tourDetail.setEmployee(employeeService.getEmployeeById(tourDetailRequestDto.getEmployeeId()));
        tourDetail.setTourist(touristService.getTouristById((tourDetailRequestDto.getTouristId())));
        return modelMapper.map(tourDetailRepository.save(tourDetail),TourDetailDto.class);
    }


    public void deleteTourDetail(Long id){
        TourDetail tourDetail=getTourDetailById(id);
        tourDetailRepository.delete(tourDetail);
    }

    public List<TourDetailDto> getTourDetailsPageByTouristId(Long Id,int no,int size){
        Pageable pageable = PageRequest.of(no-1,size);
        Page<TourDetail> tourDetails=tourDetailRepository.findAll(pageable);
        return tourDetails.toList().stream().map(tourDetail -> modelMapper.map(tourDetail,TourDetailDto.class)).collect(Collectors.toList());

    }


}
