package com.secondpartial.platformreplica.models;

import com.secondpartial.platformreplica.enums.RolEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
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
  private String userImageUrl;

  @Column(nullable = true)
  private String userImageKey;

  @Column(nullable = true)
  private String dni;

  @ManyToOne()
  @JoinColumn(name = "city_id", nullable = true)
  private CityModel city;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private StudentModel student;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private TeacherModel teacher;

  @ManyToOne()
  @JoinColumn(name = "career_id", columnDefinition = "on delete set null")
  private CareerModel career;
}
