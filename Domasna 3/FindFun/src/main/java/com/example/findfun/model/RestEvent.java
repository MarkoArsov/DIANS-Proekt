package com.example.findfun.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class RestEvent {

    private Long id;

    private String name;

    private String about;

    private String imgPath;

    private Double lat;

    private Double lng;

    private LocalDateTime date;

    public RestEvent(String name, String about, String imgPath, Double lat, Double lng, LocalDateTime date) {
        this.name = name;
        this.about = about;
        this.imgPath = imgPath;
        this.lat = lat;
        this.lng = lng;
        this.date = date;
    }

    public RestEvent(Event event){
        this.id = event.getId();
        this.name = event.getName();
        this.about = event.getAbout();
        this.imgPath = event.getImgPath();
        this.date = event.getDate();
        this.lat = event.getLat();
        this.lng = event.getLng();
    }
}
