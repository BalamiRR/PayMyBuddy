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
public class Friends {
    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(name = "Created_At")
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "Updated_At")
    private Instant updatedAt;

    @Column(name = "Email_Address")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "User_Name")
    private String userName;

    @ManyToMany
    private Set<Friends> friends;


}
