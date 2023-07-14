package com.example.TourProject.Security;

import com.example.TourProject.Service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private  JwtToken jwtToken;
    @Autowired
    private  UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

     final String authHeader=request.getHeader("Authorization");
     final String jwt;
     final String userEmail;
     if(authHeader==null || authHeader.startsWith("Bearer ")){
         filterChain.doFilter(request,response);
         return;
     }
     jwt=authHeader.substring(7);
     userEmail=jwtToken.getUsernameFromJwt(jwt);
     if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null){
         UserDetails userDetails = userService.loadUserByUsername(userEmail);
         UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
         authenticationToken.setDetails(
                 new WebAuthenticationDetailsSource().buildDetails(request)
         );
         SecurityContextHolder.getContext().setAuthentication(authenticationToken);

     }
     filterChain.doFilter(request,response);
    }




}
