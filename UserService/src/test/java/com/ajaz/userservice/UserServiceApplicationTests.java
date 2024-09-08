package com.ajaz.userservice;

import com.ajaz.userservice.external.services.RatingService;
import com.ajaz.userservice.models.Rating;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@Slf4j
class UserServiceApplicationTests {

    @Autowired
    private RatingService ratingService;

    @Test
    void contextLoads() {
    }

    @Test
    void createRating(){
        Rating rating = Rating.builder()
                .userId("6")
                .hotelId("3")
                .rating(9)
                .feedback("This is feign client feedback for meraj ahmed")
                .build();

        ResponseEntity<Rating> response = ratingService.createRating(rating);
        log.info("Create rating test response from Rating service: {}", response.getBody());

    }

}
