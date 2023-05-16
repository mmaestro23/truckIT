/*package com.maestro.truckIT.controller;

import com.maestro.truckIT.model.Driver;
import com.maestro.truckIT.model.Ordered;
import com.maestro.truckIT.model.Truck;
import com.maestro.truckIT.model.Users;
import com.maestro.truckIT.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Objects;

@Controller
public class UsersController {

    private UsersService service;

    private DriversService driversService;

    private TrucksService trucksService;

    private OrderService orderService;

    @Autowired
    public UsersController(UsersService service, DriversService driversService, TrucksService trucksService, OrderService orderService) {
        this.service = service;
        this.driversService = driversService;
        this.trucksService = trucksService;
        this.orderService = orderService;
    }






    @GetMapping("/usersForm")
    public String usersPage(Model model){
        model.addAttribute("pageTitle", "Users Form");
        model.addAttribute("users", new Users());
        return "usersForm";
    }

    @PostMapping("/usersForm/saveUser")
    public String saveUsers(Users users, RedirectAttributes ra){
        service.saveUser(users);
        ra.addFlashAttribute("message", "User Created.");
        return "redirect:/";
    }

    @GetMapping("/usersForm/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Users users = service.get(id);
            model.addAttribute("users", users);
            model.addAttribute("pageTitle", "Edit user (ID: " + id + ")");
            ra.addFlashAttribute("message", "The user with ID " + id + "has been updated");
            return "usersForm";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }

    @GetMapping("users/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The user with ID " + id + " has been deleted");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/";
    }

}*/
