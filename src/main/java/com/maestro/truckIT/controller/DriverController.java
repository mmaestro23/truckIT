package com.maestro.truckIT.controller;

import com.maestro.truckIT.model.Driver;
import com.maestro.truckIT.service.DriversService;
import com.maestro.truckIT.service.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DriverController {

    private DriversService driversService;

    @Autowired
    public DriverController(DriversService driversService) {
        this.driversService = driversService;
    }


    @GetMapping("/driverForm")
    public String driverPage(Model model){
        model.addAttribute("pageTitle", "Drivers Form");
        model.addAttribute("drivers", new Driver());
        return "driverForm";
    }

    @PostMapping("/driverForm/saveDriver")
    public String saveDrivers(Driver drivers, RedirectAttributes ra){
        driversService.save(drivers);
        ra.addFlashAttribute("messageDriver", "Driver Saved.");
        return "redirect:/dash";
    }

    @GetMapping("/driverForm/edit/{id}")
    public String showEditDriver(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Driver drivers = driversService.get(id);
            model.addAttribute("drivers", drivers);
            model.addAttribute("pageTitle", "Edit driver (ID: " + id + ")");
            ra.addFlashAttribute("messageDriver", "The driver with ID " + id + "has been updated");
            return "driverForm";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/dash";
        }
    }

    @GetMapping("driverForm/delete/{id}")
    public String deleteDriver(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            driversService.delete(id);
            ra.addFlashAttribute("messageDriver", "The driver with ID " + id + " has been deleted");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/dash";
    }
}
