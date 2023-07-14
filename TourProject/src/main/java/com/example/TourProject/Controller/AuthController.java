package com.example.TourProject.Controller;

import com.example.TourProject.Dto.Authorization.AuthRequestDto;
import com.example.TourProject.Dto.User.UserLoginDto;
import com.example.TourProject.Dto.User.UserRegisterDto;
import com.example.TourProject.Security.AuthResponse;
import com.example.TourProject.Security.JwtToken;
import com.example.TourProject.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/auth")

public class AuthController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final JwtToken jwtToken;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, ModelMapper modelMapper, JwtToken jwtToken, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.jwtToken = jwtToken;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<AuthResponse> login(@RequestBody UserLoginDto userLoginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getEmail(),
                        userLoginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtToken.generateToken(userService.loadUserByUsername(userLoginDto.getEmail()));
        return new ResponseEntity<>(new AuthResponse(token),HttpStatus.OK);
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
