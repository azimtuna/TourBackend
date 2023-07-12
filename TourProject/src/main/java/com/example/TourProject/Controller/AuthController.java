package com.example.TourProject.Controller;

import com.example.TourProject.Dto.Authorization.AuthRequestDto;
import com.example.TourProject.Dto.User.UserRegisterDto;
import com.example.TourProject.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class AuthController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public AuthController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequestDto authRequestDto){
        if(userService.isUserExist(authRequestDto.getEmail())){
            return new ResponseEntity<>("This username is in use", HttpStatus.BAD_REQUEST);
        }
        userService.register(modelMapper.map(authRequestDto, UserRegisterDto.class));
        return new ResponseEntity<>("Created",HttpStatus.CREATED);
    }



}
