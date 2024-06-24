package com.example.firstProject.controllers;

import com.example.firstProject.entity.Order;
import com.example.firstProject.entity.User;
import com.example.firstProject.entity.UserProfile;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateUserRequest {
    private User user;
    private UserProfile userProfile;
    private List<Order> orders;
}