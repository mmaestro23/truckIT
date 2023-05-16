package com.maestro.truckIT.service;

import com.maestro.truckIT.model.Driver;
import com.maestro.truckIT.model.Truck;
import com.maestro.truckIT.repo.TrucksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrucksService {
    private static TrucksRepo repo;

    @Autowired
    public TrucksService(TrucksRepo repo) {
        this.repo = repo;
    }

    public List<Truck> listAll(){
        return (List<Truck>) repo.findAll();
    }

    public void save(Truck trucks){
        repo.save(trucks);
    }


    public Truck get(Integer id) throws UserNotFoundException {
        Optional<Truck> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }throw new UserNotFoundException("Could not find truck with id " + id);
    }

    public void delete(Integer id) throws UserNotFoundException{
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("Could not find the user with id " + id);
        }
        repo.deleteById(id);
    }
}
