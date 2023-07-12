package com.example.TourProject.Dto.Authorization;

import com.example.TourProject.Model.Role;
import lombok.Data;

@Data
public class AuthRequestDto {
    private String firstname;
    private String email;
    private String password;
}
