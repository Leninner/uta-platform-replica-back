package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.models.UserModel;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {
  @Query(value = "select * from users where email = :email", nativeQuery = true)
  public UserModel getByEmail(@Param("email") String email);
}
