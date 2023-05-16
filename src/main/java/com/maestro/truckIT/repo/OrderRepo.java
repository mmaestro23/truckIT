package com.maestro.truckIT.repo;

import com.maestro.truckIT.model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Ordered, Integer> {
    public long countById(Integer id);


}
