package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.models.CareerModel;

@Repository
public interface CareerRepository extends JpaRepository<CareerModel, Long> {

    @Query(value = "select * from careers c where c.name = :name", nativeQuery = true)
    public CareerModel findByName(@Param("name") String name);

}
