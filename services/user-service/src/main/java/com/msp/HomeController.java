package com.msp;

import com.msp.payloads.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse HomeController() {
        ApiResponse apiResponse =
                new ApiResponse("Hey everyone! I am the User Service within Airlines Microservices project!");
        return apiResponse;
    }
}
