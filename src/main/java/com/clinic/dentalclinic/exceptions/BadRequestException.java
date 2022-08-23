package com.clinic.dentalclinic.exceptions;

public class BadRequestException extends Exception{
    public BadRequestException (String message){
        super(message);
    }
}
