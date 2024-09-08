package com.ajaz.microservices.ratingservice.repositories;

import com.ajaz.microservices.ratingservice.models.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {
    List<Rating> findAllByUserId(String userId);
    List<Rating> findAllByHotelId(String hotelId);
}
