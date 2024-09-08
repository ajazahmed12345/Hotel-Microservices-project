package com.ajaz.userservice.external.services;

import com.ajaz.userservice.models.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    @PostMapping("/api/ratings")
    ResponseEntity<Rating> createRating(Rating rating);
}
