package com.sam.emedia.user.controllers;

import com.sam.emedia.user.components.HttpServletComponent;
import com.sam.emedia.user.entities.Address;
import com.sam.emedia.user.entities.User;
import com.sam.emedia.user.models.ResponseObject;
import com.sam.emedia.user.models.UserLogin;
import com.sam.emedia.user.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user/")
public class UserController {

    final HttpServletComponent servletComponent;

    final Environment environment;
    final UserService userService;
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


    @PostMapping("/address")
    public ResponseEntity<ResponseObject> addAddress(@RequestBody Address address) {
        return ResponseEntity.status(CREATED).body(userService.saveAddress(address, servletComponent.getUserId()));
    }

    /**
     * Getting all the address of users
     * **/
    @GetMapping("/address")
    public ResponseEntity<ResponseObject> getAddress() {
        return ResponseEntity.status(CREATED).body(userService.getAddresses(servletComponent.getUserId()));
    }

    /**
     * Getting all the default address of users
     * **/
    @GetMapping("/address/default")
    public ResponseEntity<ResponseObject> addAddress() {
        return ResponseEntity.status(CREATED).body(userService.getDefaultAddress(servletComponent.getUserId()));
    }

    @PutMapping("/address/default/{addressId}")
    public ResponseEntity<ResponseObject> defaultAddress(@PathVariable int addressId) {
        return ResponseEntity.status(OK).body(userService.changeDefaultAddress(servletComponent.getUserId(), addressId));
    }

    @DeleteMapping("/address/{addressId}")
    public ResponseEntity<ResponseObject> deleteAddress(@PathVariable int addressId) {
        ResponseObject responseObject = userService.deleteAddress(addressId);
        if(responseObject.isSuccess())
            return ResponseEntity.status(OK).body(responseObject);
        return ResponseEntity.status(NOT_MODIFIED).body(responseObject);
    }

}
