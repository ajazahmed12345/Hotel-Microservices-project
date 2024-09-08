package com.ajaz.microservices.ratingservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingNotFoundException extends Exception{
    public RatingNotFoundException(String message){
        super(message);
    }
}
