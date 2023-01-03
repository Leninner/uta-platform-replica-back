package com.secondpartial.platformreplica.models;

import com.secondpartial.platformreplica.enums.RolEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String address;

  @Column(nullable = true)
  @Enumerated(EnumType.STRING)
  private RolEnum rol;

  @Column(name = "phone_number", nullable = true)
  private String phoneNumber;

  @Column(nullable = true)
  private String image;

  @ManyToOne()
  @JoinColumn(name = "city_id")
  private CityModel city;

  @OneToOne(mappedBy = "user")
  private StudentModel student;

  @OneToOne(mappedBy = "user")
  private TeacherModel teacher;
}
