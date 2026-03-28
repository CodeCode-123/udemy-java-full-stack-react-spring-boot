package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/dummy")
public class DummyController {
    @PostMapping("/create-user")
    public String createUser(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        return "User created successfully";
    }

    @GetMapping("/search")
    public String searchUser(@RequestParam(required = false, defaultValue = "Guest",
            name = "name") String userName) { // if define name="username", search?username=Madan will work
        // If did not provide the @RequestParam without setting required=false, return 404
        // otherwise, may return "Search for user: Guest"
        // If you want to use a different variable name for your method input arguments,
        // you need to define variable name
        return "Searching for user: " + userName;
    }

//    @GetMapping("/multiple-search")
//    public String multipleSearch(@RequestParam String firstName, @RequestParam String lastName) {
//        return "Searching for user: " + firstName + " " + lastName;
//    }

    @GetMapping("/multiple-search")
    public String multipleSearch(@RequestParam Map<String, String> params) {
        // define the params in the method using params.get()
        return "Searching for user: " + params.get("firstName") + " " + params.get("lastName");
    }

    // multiple path definitions, use {}, and set required=false
    @GetMapping({"/user/{userId}/posts/{postId}", "/user/{userId}"})
    public String getUser(@PathVariable(name="userId") String id,
                          @PathVariable(required = false) String postId) {
        return "Searching for user with userId: " + id + " and post: " + postId;
    }

    // limitation of @PathVariable, you can't set the default value
    @GetMapping({"/user/map/{userId}/posts/{postId}", "/user/map/{userId}"})
    public String getUserUsingMap(@PathVariable Map<String, String> pathVariables) {
        return "Searching for user with userId: " + pathVariables.get("userId")
                + " and post: " + pathVariables.get("postId");
    }
}
