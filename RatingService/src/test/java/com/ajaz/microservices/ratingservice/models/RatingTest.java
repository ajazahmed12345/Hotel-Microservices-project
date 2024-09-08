package com.ajaz.microservices.ratingservice.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RatingTest {

    @InjectMocks
    private Rating rating;

    @Test
    public void testGetRatingId() {
        rating.getRatingId();
    }

    @Test
    public void testGetHotelId() {
        rating.getHotelId();
    }

    @Test
    public void testGetUserId() {
        rating.getUserId();
    }

    @Test
    public void testGetRating() {
        rating.getHotelId();
    }

    @Test
    public void testGetFeedback() {
        rating.getFeedback();
    }

    @Test
    public void testSetRatingId() {
        rating.setRatingId("123");
    }

    @Test
    public void testSetHotelId() {
        rating.setHotelId("234");
    }

    @Test
    public void testSetUserId() {
        rating.setUserId("456");
    }

    @Test
    public void testSetRating() {
        rating.setRating(5);
    }

    @Test
    public void testSetFeedback() {
        rating.setFeedback("random feedback");
    }
}