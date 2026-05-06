package com.shreya.farmeradvisory.exception;

public class FarmerNotFoundException extends RuntimeException{
    public FarmerNotFoundException(String message){
        super(message);
    }
}
