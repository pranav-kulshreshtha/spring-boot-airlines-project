package com.msp.controllers;

import com.msp.payloads.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse HomeController() {
        ApiResponse apiResponse = new ApiResponse("Hey, I am the Flight Ops service!");
        return apiResponse;
    }
}
