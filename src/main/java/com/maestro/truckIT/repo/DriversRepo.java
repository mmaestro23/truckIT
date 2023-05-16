package com.maestro.truckIT.repo;

import com.maestro.truckIT.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriversRepo extends JpaRepository<Driver, Integer> {
    public long countById(Integer id);
}
