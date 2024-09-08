package com.ajaz.userservice.models;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Rating {
    private String ratingId;
    private String hotelId;
    private String userId;
    private int rating;
    private String feedback;
    private Hotel hotel;
}
