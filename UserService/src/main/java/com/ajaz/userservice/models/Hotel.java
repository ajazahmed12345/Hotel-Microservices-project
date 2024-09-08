package com.ajaz.userservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hotel {
    private Long id;
    private String name;
    private String address;
    private String about;
    private List<Room> rooms = new ArrayList<>();
}
