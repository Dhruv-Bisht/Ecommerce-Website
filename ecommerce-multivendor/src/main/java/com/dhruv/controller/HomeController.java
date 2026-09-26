package com.dhruv.controller;

import com.dhruv.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // class acts as controller
public class HomeController {

    @GetMapping  // Just like Methods GET,PUT,PATCH,DELETE,POST
    /*
    *@GetMapping: Fetches/retrieves data from a server.
    *@PostMapping: Creates or submits new resources to the database.
    *@PutMapping: Updates or entirely replaces existing data.
    *@PatchMapping: Applies partial updates to an existing resource.
    *@DeleteMapping: Removes data from the database.
    * */
    // will return the ApiResponse which we have created.
    public ApiResponse HomeControllerHandler(){
        ApiResponse apiResponse = new ApiResponse();  // creating the object of the ApiResponse.
        apiResponse.setMessage("Welcome to Ecommerce multivendor system.");  // setting the message.
        return apiResponse; // returing the response.
    }
}
