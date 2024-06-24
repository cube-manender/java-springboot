package com.example.firstProject.controllers;

import com.example.firstProject.entity.Order;
import com.example.firstProject.entity.User;
import com.example.firstProject.entity.UserProfile;
import com.example.firstProject.repositories.OrderRepository;
import com.example.firstProject.repositories.UserProfileRepository;
import com.example.firstProject.repositories.UserRepository;
import com.example.firstProject.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/users")
    public Iterable<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/fixUsers")
    public Iterable<User> fixUsers() {
        return userService.getAllUsersAndFixNullFields();
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @PostMapping("/addUserDetails")
    public User addUserDetails(@NotNull @RequestBody CreateUserRequest request) {
        User user = request.getUser();
        UserProfile userProfile = request.getUserProfile();
        List<Order> orders = request.getOrders();

        // Set relationships
        user.setUserProfile(userProfile);
        userProfile.setUser(user);
        user.setOrders(orders);
        orders.forEach(order -> order.setUser(user));

        // Save entities
        userRepository.save(user);
        userProfileRepository.save(userProfile);
        orderRepository.saveAll(orders);

        return user;
    }

    @PostMapping("/addOrder/{userId}")
    public Order addOrder(@PathVariable Integer userId, @RequestBody Order order) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        order.setUser(user);
        return orderRepository.save(order);
    }
}