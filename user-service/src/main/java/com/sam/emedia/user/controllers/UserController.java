package com.sam.emedia.user.controllers;

import com.sam.emedia.user.entities.User;
import com.sam.emedia.user.models.ResponseObject;
import com.sam.emedia.user.models.UserLogin;
import com.sam.emedia.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    Environment environment;

    @Autowired
    UserService userService;
    @GetMapping("/test")
    public String testService() {
        return "Response from auth-service running at port: " + environment.getProperty("local.server.port");
    }


    @PostMapping()
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        System.out.println("user registration");
        return ResponseEntity.status(CREATED).body(userService.registerUser(user));
    }

    @GetMapping()
    public ResponseEntity<Object> getUser(@RequestParam(name = "id", defaultValue = "0", required = false) int userId) {
        HashMap<String,Object> resMap = new HashMap<>();
        resMap.put("success", true);
        if(userId != 0)
            resMap.put("user", userService.getUserById(userId));
        else {
            resMap.put("users", userService.getUsers());
        }
        return ResponseEntity.ok(resMap);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody UserLogin login) {

        return ResponseEntity.status(CREATED).body(userService.loginUser(login));
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteUser(@RequestParam(name = "id", required = true) int userId) {
        return null;
    }
}
