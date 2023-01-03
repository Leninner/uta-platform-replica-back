package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.models.CityModel;

@Repository
public interface CityRepository extends JpaRepository<CityModel, Long> {
  
}
