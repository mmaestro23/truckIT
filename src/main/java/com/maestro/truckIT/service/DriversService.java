package com.maestro.truckIT.service;

import com.maestro.truckIT.model.Driver;
import com.maestro.truckIT.model.Users;
import com.maestro.truckIT.repo.DriversRepo;
import com.maestro.truckIT.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriversService {
    private static DriversRepo repo;

    @Autowired
    public DriversService(DriversRepo repo) {
        this.repo = repo;
    }

    public List<Driver> listAll(){
        return (List<Driver>) repo.findAll();
    }

    public void save(Driver drivers){
        repo.save(drivers);
    }


    public Driver get(Integer id) throws UserNotFoundException {
        Optional<Driver> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }throw new UserNotFoundException("Could not find the user with id " + id);
    }

    public void delete(Integer id) throws UserNotFoundException{
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("Could not find the user with id " + id);
        }
        repo.deleteById(id);
    }
}
