package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.dto.UserDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/dummy")
public class DummyController {
    @PostMapping("/create-user")
    public String createUser(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        return "User created successfully";
    }

    @PostMapping("/request-entity")
    public String createUserWithEntity(RequestEntity<UserDto> requestEntity) {
        // RequestEntity can get both request header and request body
        HttpHeaders headers = requestEntity.getHeaders();
        UserDto userDto = requestEntity.getBody();
        //String queryString = requestEntity.getUrl().getQuery();
        //String queryString = requestEntity.getUrl().getPath();
        return "User created successfully";
    }

    @GetMapping("/headers")
    public String readHeaders(@RequestHeader(name="User-Agent") String userAgent,
                              @RequestHeader(name="User-Location", required = false) String userLocation) {
        // list of HTTP header fields, e.g. Host, User-Agent, Accept, Connection
        return "Received headers with value: " + userAgent + ": " + userLocation;
    }

    @GetMapping("/headers/map")
    public String readHeadersUsingMap(@RequestHeader Map<String, String> headers) {
        // list of HTTP header fields, e.g. Host, User-Agent, Accept, Connection
        // use map to get() fields, headers.get("User-Agent")
        return "Received headers with value: " + headers.toString();
    }

    @GetMapping("/headers/httpheaders")
    public String readHeadersUsingHttpHeaders(@RequestHeader HttpHeaders headers) {
        // HttpHeaders implements MultivalueMap<String, String>
        List<String> location = headers.get("User-Location");
        return "Received headers with value: " + headers.toString();
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
