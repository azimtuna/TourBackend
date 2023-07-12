package com.example.TourProject.Service;

import com.example.TourProject.Dto.Tourist.TouristDto;
import com.example.TourProject.Dto.Tourist.TouristRequestDto;
import com.example.TourProject.Exceptions.NotFoundException;
import com.example.TourProject.Model.Tourist;
import com.example.TourProject.Repository.TouristRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TouristService {
    private final ModelMapper modelMapper;
    private final TouristRepository touristRepository;

    public TouristService(ModelMapper modelMapper, TouristRepository touristRepository) {
        this.modelMapper = modelMapper;
        this.touristRepository = touristRepository;
    }


    protected Tourist getTouristById(Long id){
        return touristRepository.findById(id).orElseThrow(()->new NotFoundException("Tourist"));
    }

    public List<TouristDto> getAll(){
        List<Tourist> tourists= touristRepository.findAll();
        return tourists.stream().map(tourist -> modelMapper.map(tourist,TouristDto.class)).collect(Collectors.toList());

    }

    public TouristDto createTourist(TouristRequestDto touristRequestDto){
        Tourist tourist= modelMapper.map(touristRequestDto,Tourist.class);
        tourist.setTouristId(0);
        return modelMapper.map(touristRepository.save(tourist),TouristDto.class);
    }
    public TouristDto getOneTourist(Long id){
        return modelMapper.map(getTouristById(id),TouristDto.class);
    }
    public TouristDto updateTourist(Long id,TouristRequestDto touristRequestDto){
        Tourist tourist= getTouristById(id);
        tourist=modelMapper.map(touristRequestDto,Tourist.class);
        return modelMapper.map(touristRepository.save(tourist),TouristDto.class);
    }

    public void deleteTourist(Long id ){
        Tourist tourist=getTouristById(id);
        touristRepository.delete(tourist);
    }






}
