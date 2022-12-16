package com.secondpartial.platformreplica.models;

import jakarta.persistence.*;

@Entity
@Table(name = "cities")
public class CityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "cities", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserModel> users;

    private String name;
}
