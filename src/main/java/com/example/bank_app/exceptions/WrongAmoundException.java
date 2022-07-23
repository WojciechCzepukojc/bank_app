package com.example.bank_app.exceptions;

public class WrongAmoundException extends RuntimeException{

    public WrongAmoundException(String meessage){
        super(meessage);
    }
}
