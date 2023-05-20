package com.maestro.truckIT.service;

import com.maestro.truckIT.model.Ordered;
import com.maestro.truckIT.repo.DriversRepo;
import com.maestro.truckIT.repo.OrderRepo;
import com.maestro.truckIT.repo.TrucksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private static OrderRepo repo;

    private static TrucksRepo truckRepo;

    private static DriversRepo driverRepo;

    @Autowired
    public OrderService(OrderRepo repo) {
        this.repo = repo;
    }

    public List<Ordered> listAll(){
        return (List<Ordered>) repo.findAll();
    }

    /*public void save(Ordered ordered){
        repo.save(ordered);
    }*/

    public Ordered save(Ordered ordered) throws UserNotFoundException {
        /*if (ordered.getDriver() != null) {
            int driverId = Math.toIntExact(ordered.getDriver().getId());
            Driver driver = repo.findById(driverId)
                    .orElseThrow(() -> new UserNotFoundException("Driver not found")).getDriver();
            ordered.setDriver(driver);
        }

        if (ordered.getTruck() != null) {
            int truckId = Math.toIntExact(ordered.getTruck().getId());
            Truck truck = repo.findById(truckId)
                    .orElseThrow(() -> new UserNotFoundException("Truck not found")).getTruck();
            ordered.setTruck(truck);
        }*/

        repo.save(ordered);
        return ordered;
    }
}
