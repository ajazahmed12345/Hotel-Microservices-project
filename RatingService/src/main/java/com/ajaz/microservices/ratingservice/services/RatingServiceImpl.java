package com.ajaz.microservices.ratingservice.services;

import com.ajaz.microservices.ratingservice.exceptions.RatingNotFoundException;
import com.ajaz.microservices.ratingservice.models.Rating;
import com.ajaz.microservices.ratingservice.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ratingServiceImpl")
public class RatingServiceImpl implements RatingService{
    private RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }
    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {

        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) throws RatingNotFoundException{

        List<Rating> ratings = ratingRepository.findAllByUserId(userId);
        if(ratings.isEmpty()){
            throw new RatingNotFoundException("No ratings found with the userId: " + userId);
        }

        return ratings;
    }

    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        return ratingRepository.findAllByHotelId(hotelId);
    }
}
