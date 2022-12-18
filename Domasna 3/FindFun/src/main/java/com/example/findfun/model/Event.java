package com.example.findfun.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String about;

    private String imgPath;

    private Double lat;

    private Double lng;

    public Event(String name, String about, String imgPath, Double lat, Double lng) {
        this.name = name;
        this.about = about;
        this.imgPath = imgPath;
        this.lat = lat;
        this.lng = lng;
    }

    public Event() {

    }
}
