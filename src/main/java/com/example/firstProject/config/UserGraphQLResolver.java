package com.example.firstProject.config;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.firstProject.controllers.CreateUserRequest;
import com.example.firstProject.entity.User;
import com.example.firstProject.service.UserGraphQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserGraphQLResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final UserGraphQLService userService;

    @Autowired
    public UserGraphQLResolver(UserGraphQLService userService) {
        this.userService = userService;
    }

    public User getUser(int id) {
        return userService.getUserById(id);
    }

    public Iterable<User> getAllUser() {
        return userService.getAllUsers();
    }

    public User createUser(CreateUserRequest userInput) {
        return userService.createUser(userInput);
    }
}