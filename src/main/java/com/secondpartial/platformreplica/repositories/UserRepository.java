package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
  @Query(value = "select u.id as id, c.id as city_id, address, email, rol, u.name as name, c.name as city, p.name as province, image, phone_number, password, ce.id as career_id from users u inner join careers ce on ce.id = u.career_id inner join cities c on c.id = u.city_id inner join provinces p on p.id = c.province_id where email = :email", nativeQuery = true)
  public UserModel getByEmail(@Param("email") String email);
}
