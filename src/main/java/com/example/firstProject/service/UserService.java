package com.example.firstProject.service;

import com.example.firstProject.entity.User;
import com.example.firstProject.repositories.UserRepository;
import com.example.firstProject.utils.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getAllUsersAndFixNullFields() {
        Iterable<User> users = userRepository.findAll();
        List<User> usersToUpdate = new ArrayList<>();

        users.forEach(user -> {
            boolean updated = false;
            if (user.getFirstName() == null) {
                user.setFirstName(UserUtility.generateRandomString());
                updated = true;
            }
            if (user.getLastName() == null) {
                user.setLastName(UserUtility.generateRandomString());
                updated = true;
            }
            if (updated) {
                usersToUpdate.add(user);
            }
        });

        if (!usersToUpdate.isEmpty()) {
            userRepository.saveAll(usersToUpdate);
        }
        return users;
    }

}
