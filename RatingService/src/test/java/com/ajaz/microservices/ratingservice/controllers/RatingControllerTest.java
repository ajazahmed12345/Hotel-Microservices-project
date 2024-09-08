package com.ajaz.microservices.ratingservice.controllers;

import com.ajaz.microservices.ratingservice.models.Rating;
import com.ajaz.microservices.ratingservice.services.RatingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RatingControllerTest {

    @InjectMocks
    private RatingController ratingController;

    @Mock
    private RatingService ratingService;
    @Test
    public void testCreateRating(@Mock Rating rating) {

        ratingController.createRating(rating);
    }

    @Test
    public void testGetAllRatings() {
    }

    @Test
    public void testGetRatingsByHotelId() {
    }

    @Test
    public void testGetRatingsByUserId() {
    }
}