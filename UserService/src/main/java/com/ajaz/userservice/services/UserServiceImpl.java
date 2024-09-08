package com.ajaz.userservice.services;

import com.ajaz.userservice.dtos.UserDto;
import com.ajaz.userservice.exceptions.ApiResponse;
import com.ajaz.userservice.exceptions.UserNotFoundException;
import com.ajaz.userservice.external.services.HotelService;
import com.ajaz.userservice.models.Hotel;
import com.ajaz.userservice.models.Rating;
import com.ajaz.userservice.models.User;
import com.ajaz.userservice.repositories.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
@Slf4j
public class UserServiceImpl implements UserService{

    @Value("${rating.service.url}")
    private String ratingServiceUrl;

    @Value("${hotel.service.url}")
    private String hotelServiceUrl;

    private UserRepository userRepository;

    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate){
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws UserNotFoundException{
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User with id: " + id + " not found in Database.");
        }

//        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
//        RestTemplate restTemplate = restTemplateBuilder.build();

        log.info("Url for rating service: {}", ratingServiceUrl + id);

        Rating[] response = restTemplate.getForObject(ratingServiceUrl + id, Rating[].class);

        List<Rating> ratings = Arrays.stream(response).collect(Collectors.toList());

        log.info("Ratings fetched from RATING SERVICE: {}", ratings);

        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//        for(Rating rating : ratings){
//            String hotelId = rating.getHotelId();
//            ResponseEntity<Hotel> hotelResponse = restTemplate.getForEntity(hotelServiceUrl + hotelId, Hotel.class);
//            log.info("{}", hotelResponse.getBody());
//            rating.setHotel(hotelResponse.getBody());
//        }
        ratings.stream().map(rating -> {
//            ResponseEntity<Object>  hotelResponse = restTemplate.getForEntity(hotelServiceUrl + rating.getHotelId(), Object.class);
//            Hotel hotel = mapper.convertValue(hotelResponse.getBody(), Hotel.class);
            log.info("hotelId: {}", rating.getHotelId());
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        User user = userOptional.get();

        user.setRatings(ratings);

        return user;
    }

    @Override
    public User updateUserByUserId(Long userId, UserDto userDto) throws UserNotFoundException {

        Optional<User>  userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User you are trying to update with id: " + userId + " does not exist.");
        }

        User existingUser = userOptional.get();
        existingUser.setName(checkNullOrEmpty(userDto.getName()) ? existingUser.getName() : userDto.getName());
        existingUser.setEmail(checkNullOrEmpty(userDto.getEmail()) ? existingUser.getEmail() : userDto.getEmail());
        existingUser.setPhoneNumber(checkNullOrEmpty(userDto.getPhoneNumber()) ? existingUser.getPhoneNumber() : userDto.getPhoneNumber());
        existingUser.setAddress(checkNullOrEmpty(userDto.getAddress()) ? existingUser.getAddress() : userDto.getAddress());

        return userRepository.save(existingUser);

    }

    @Override
    public ApiResponse deleteUserById(Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User you are trying to delete with id: " + id + " does not exist.");
        }

        userRepository.deleteById(id);

        ApiResponse response = ApiResponse.builder()
                .message("User with id: " + id + " deleted successfully.")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();

        return response;

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersWithEmailEnding(String regex) {
        return userRepository.findAllByEmailContaining(regex);
    }

    public boolean checkNullOrEmpty(String str){
        if(null == str || str.isEmpty())
            return true;

        return false;
    }


}
