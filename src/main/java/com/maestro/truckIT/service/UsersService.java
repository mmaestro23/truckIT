package com.maestro.truckIT.service;

import com.maestro.truckIT.model.Role;
import com.maestro.truckIT.model.Users;
import com.maestro.truckIT.repo.RoleRepo;
import com.maestro.truckIT.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsersService {
    private static UsersRepo repo;
    private static RoleRepo roleRepo;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepo repo, RoleRepo roleRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repo = repo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    /*public static Users authenticate(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }*/

    public List<Users> listAll(){
        return (List<Users>) repo.findAll();
    }

    public void saveUser(Users users){
        Users user = new Users();
        user.setUsername(users.getUsername());
        user.setEmail(users.getEmail());
        user.setPassword(passwordEncoder.encode(users.getPassword()));
        Role role = roleRepo.findRoleByName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        repo.save(user);
    }

    public static Users findByEmail(String email){
        return repo.findByEmail(email);
    }

    public static Users findByUsername(String username){

        return repo.findByUsername(username);
    }
    public static Users users(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }

    public Users get(Integer id) throws UserNotFoundException {
        Optional<Users> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }throw new UserNotFoundException("Could not find the user with id " + id);
    }

    /*@Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        Users user = repo.findByUserName(userName);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }*/

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public void delete(Integer id) throws UserNotFoundException{
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("Could not find the user with id " + id);
        }
        repo.deleteById(id);
    }
}
