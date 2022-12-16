package com.secondpartial.platformreplica.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserModel {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String city;
    private String address;
    private String phoneNumber;
}
