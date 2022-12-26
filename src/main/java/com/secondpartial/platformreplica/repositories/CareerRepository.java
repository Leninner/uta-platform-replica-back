package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.models.CareerModel;

@Repository
public interface CareerRepository extends JpaRepository<CareerModel, Long>{
  
}
