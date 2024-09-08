package com.ajaz.microservices.ratingservice.controllers;

import com.ajaz.microservices.ratingservice.exceptions.RatingNotFoundException;
import com.ajaz.microservices.ratingservice.models.Rating;
import com.ajaz.microservices.ratingservice.services.RatingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    private RatingService ratingService;

    public RatingController(@Qualifier("ratingServiceImpl") RatingService ratingService){
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        return new ResponseEntity<>(ratingService.createRating(rating), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Rating> getAllRatings(){
        return ratingService.getAllRatings();
    }

    @GetMapping("/hotels/{hotelId}")
    public List<Rating> getRatingsByHotelId(@PathVariable("hotelId") String hotelId){
        return ratingService.getRatingsByHotelId(hotelId);
    }

    @GetMapping("/users/{userId}")
    public List<Rating> getRatingsByUserId(@PathVariable("userId") String userId) throws RatingNotFoundException {
        return ratingService.getRatingsByUserId(userId);
    }

}
