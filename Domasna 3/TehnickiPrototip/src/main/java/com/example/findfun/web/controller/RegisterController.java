package com.example.tehnickiprototip.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class RegisterController {

    @GetMapping
    public String signup(){
        return "signUp";
    }
}
