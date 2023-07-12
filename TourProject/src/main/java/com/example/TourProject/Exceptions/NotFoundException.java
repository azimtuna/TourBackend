package com.example.TourProject.Exceptions;

public class NotFoundException extends RuntimeException{
private String className;
private String details;

    public NotFoundException(String className, String details) {
        this.className = className;
        this.details = details;
    }

    public NotFoundException(String className){
        this.className = className;
    }

    public String getMassage(){
        return this.className+" Can Not Be Found";
    }
    public String getDetails(){
        return details;
    }

}
