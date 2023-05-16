package com.maestro.truckIT.service;

import com.maestro.truckIT.model.Role;
import com.maestro.truckIT.repo.RoleRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private static RoleRepo repo;

    @Autowired
    private EntityManager entityManager;

    public Role findRoleByName(String name){
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("from roles where name=:roleName", Role.class);
        theQuery.setParameter("roleName", name);

        Role theRole = null;

        try{
            theRole = (Role) theQuery.getSingleResult();
        } catch (Exception e){
            theRole = null;
        }
        return theRole;
    }
}
