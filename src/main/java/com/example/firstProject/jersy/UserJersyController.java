package com.example.firstProject.jersy;

import com.example.firstProject.entity.User;
import com.example.firstProject.repositories.UserRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class UserJersyController {
    private final UserRepository userRepository;

    @Autowired
    public UserJersyController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GET
    @Path("/jerRoute")
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @POST
    @Path("/jerAddUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }
}
