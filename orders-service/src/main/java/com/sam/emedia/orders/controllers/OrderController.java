package com.sam.emedia.orders.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {
    @GetMapping("test")
    public static String testAPI() {
        return "Test response from Orders";
    }
}
