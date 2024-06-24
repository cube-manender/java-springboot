package com.example.firstProject.controllers;

import com.example.firstProject.service.UserRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RestRedisController {
    private final UserRedisService userRedisService;

    @Autowired
    public RestRedisController(UserRedisService redisService) {
        this.userRedisService = redisService;
    }

    @PostMapping("/set")
    public String setValue(@RequestParam String key, @RequestBody Object value) {
       return userRedisService.setValue(key, value);
    }

    @GetMapping("/get")
    public String getValue(@RequestParam String key) {
        return (String) userRedisService.getValue(key);
    }
}