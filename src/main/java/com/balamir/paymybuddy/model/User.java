package com.balamir.paymybuddy.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "user")
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

    @Column(name = "user_name")
    private String userName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private Set<Friends> friends;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user",  cascade = CascadeType.ALL, orphanRemoval = true)
    private Account account;
}
