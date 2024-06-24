package com.example.firstProject.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private UserProfile userProfile;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;

    @Column(name = "createdAt", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updatedAt", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

//    When Jackson (the JSON processor used by Spring Boot) tries to serialize your entities (User, Order, etc.) into JSON,
//    It recursively follows the bidirectional relationships, resulting in a loop
//    To solve this, you need to break the infinite loop by either ignoring one side of the bidirectional relationship during serialization or using DTOs (Data Transfer Objects) to control what gets serialized.
//    Option 1: Use @JsonIgnoreProperties or @JsonIgnore
//    Option 2: Use DTOs (Data Transfer Objects)
}