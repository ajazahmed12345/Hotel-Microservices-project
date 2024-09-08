package com.ajaz.userservice.external.services;

import com.ajaz.userservice.models.Hotel;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTELSERVICE", url = "http://localhost:8087")
public interface HotelService {


    @GetMapping("/api/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);
}
