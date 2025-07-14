package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "test"; // test.jsp
    }
}
