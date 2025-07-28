package com.balamir.paymybuddy.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "email_address")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "User_Name")
    private String userName;

    @ManyToMany
    private Set<Friends> friends;

}
