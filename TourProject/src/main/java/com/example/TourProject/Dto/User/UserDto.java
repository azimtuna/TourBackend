package com.example.TourProject.Dto.User;

import com.example.TourProject.Model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserDto {
    private String firstname;
    private String email;
    private String password;
    private Role role;
}
