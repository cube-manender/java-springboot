package com.example.firstProject.service;

import com.example.firstProject.controllers.CreateUserRequest;
import com.example.firstProject.entity.User;
import com.example.firstProject.entity.UserProfile;
import com.example.firstProject.entity.Order;
import com.example.firstProject.repositories.OrderRepository;
import com.example.firstProject.repositories.UserProfileRepository;
import com.example.firstProject.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserGraphQLService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public UserGraphQLService(UserRepository userRepository, UserProfileRepository userProfileRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.orderRepository = orderRepository;
    }

    public Iterable<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User createUser(CreateUserRequest userInput) {
        User user = new User();
        user.setFirstName(userInput.getUser().getFirstName());
        user.setLastName(userInput.getUser().getLastName());

        UserProfile userProfile = new UserProfile();
        userProfile.setAddress(userInput.getUserProfile().getAddress());
        userProfile.setPhoneNumber(userInput.getUserProfile().getPhoneNumber());
        user.setUserProfile(userProfile);

        List<Order> orders = userInput.getOrders().stream().map(orderInput -> {
            Order order = new Order();
            order.setOrderDetails(orderInput.getOrderDetails());
            order.setUser(user);
            return order;
        }).collect(Collectors.toList());
        user.setOrders(orders);

        userProfile.setUser(user);
        userProfileRepository.save(userProfile);

        orders.forEach(orderRepository::save);

        return userRepository.save(user);
    }
}
