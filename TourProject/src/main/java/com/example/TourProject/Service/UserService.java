package com.example.TourProject.Service;

import com.example.TourProject.Dto.User.UserDto;
import com.example.TourProject.Dto.User.UserRegisterDto;
import com.example.TourProject.Exceptions.NotFoundException;
import com.example.TourProject.Model.Role;
import com.example.TourProject.Model.User;
import com.example.TourProject.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(()->new NotFoundException("user "));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(user.getRole()));
    }

    public Collection<SimpleGrantedAuthority> getAuthorities(Role role){
        Set<SimpleGrantedAuthority> authorities=new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+role.toString()));
        return authorities;
    }

    public UserDto register(UserRegisterDto userRegisterDto){
        User user=modelMapper.map(userRegisterDto,User.class);
        user.setId(null);
        user.setEmail(userRegisterDto.getEmail());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setFirstname(userRegisterDto.getFirstname());
        userRepository.save(user);
        return modelMapper.map(user,UserDto.class);
    }
    public UserDto getOneUserByUserName(String username){
        User user = userRepository.findByEmail(username).orElseThrow(()->new NotFoundException("user"));
        return modelMapper.map(user,UserDto.class);
    }
    public boolean isUserExist(String username){
        if(userRepository.existsByEmail(username))
            return true;
        return false;
    }

}
