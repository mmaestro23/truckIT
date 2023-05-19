package com.maestro.truckIT.controller;

import com.maestro.truckIT.model.Truck;
import com.maestro.truckIT.service.DriversService;
import com.maestro.truckIT.service.TrucksService;
import com.maestro.truckIT.service.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TruckController {

    private TrucksService trucksService;

    @Autowired
    public TruckController(TrucksService trucksService) {
        this.trucksService = trucksService;
    }


    @GetMapping("/trucksForm")
    public String trucksPage(Model model){
        model.addAttribute("pageTitle", "Trucks Form");
        model.addAttribute("trucks", new Truck());
        return "trucksForm";
    }

    @PostMapping("/trucksForm/saveTruck")
    public String saveTrucks(Truck trucks, RedirectAttributes ra){
        trucksService.save(trucks);
        ra.addFlashAttribute("messageTruck", "Truck Saved.");
        return "redirect:/dash";
    }

    @GetMapping("/trucksForm/edit/{id}")
    public String showEditTruck(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Truck trucks = trucksService.get(id);
            model.addAttribute("trucks", trucks);
            model.addAttribute("pageTitle", "Edit a truck (ID: " + id + ")");
            ra.addFlashAttribute("messageTruck", "Truck with ID " + id + "has been updated");
            return "trucksForm";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/dash";
        }
    }

    @GetMapping("trucksForm/delete/{id}")
    public String deleteTruck(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            trucksService.delete(id);
            ra.addFlashAttribute("messageTruck", "Truck with ID " + id + " has been deleted");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/dash";
    }
}
