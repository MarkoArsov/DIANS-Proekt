package com.example.findfun.model;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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

    private String category;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;

    @ManyToOne
    private User createdUser;

    @ManyToMany
    private List<User> invitedUsers;

    @ManyToMany
    private List<User> interestedUsers;

    @OneToMany(mappedBy = "event")
    private List<Comment> comments;

    public Event(String name, String about, String imgPath, Double lat, Double lng, LocalDateTime date, User createdUser, String category) {
        this.name = name;
        this.about = about;
        this.imgPath = imgPath;
        this.lat = lat;
        this.lng = lng;
        this.date = date;
        this.category = category;
        this.createdUser = createdUser;
        this.invitedUsers = new ArrayList<>();
        this.interestedUsers = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Event(String name, String about, String imgPath, Double lat, Double lng) {
        this.name = name;
        this.about = about;
        this.imgPath = imgPath;
        this.lat = lat;
        this.lng = lng;
    }

    public Event() {

    }

    public boolean isOver(){
        if (date == null) return false;
        return date.isBefore(LocalDateTime.now());
    }
}
