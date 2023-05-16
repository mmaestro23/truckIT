package com.maestro.truckIT.repo;

import com.maestro.truckIT.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrucksRepo extends JpaRepository<Truck, Integer> {
    public long countById(Integer id);
}