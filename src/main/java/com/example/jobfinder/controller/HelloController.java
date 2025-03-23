package com.example.jobfinder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Привет, всем кто смотрит это у меня все хорошо, пишу диплом, отдыхаю в общем сказка.";
    }
}
