package com.maestro.truckIT.controller;

import com.maestro.truckIT.config.FileUploadUtil;
import com.maestro.truckIT.model.Driver;
import com.maestro.truckIT.model.Ordered;
import com.maestro.truckIT.model.Truck;
import com.maestro.truckIT.service.DriversService;
import com.maestro.truckIT.service.OrderService;
import com.maestro.truckIT.service.TrucksService;
import com.maestro.truckIT.service.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class OrderedController {

    private DriversService driversService;

    private TrucksService trucksService;
    private OrderService orderService;

    @Autowired
    public OrderedController(DriversService driversService, TrucksService trucksService, OrderService orderService) {
        this.driversService = driversService;
        this.trucksService = trucksService;
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public String orderPage(Model model){

        // Retrieve truck and driver information from the database
        List<Driver> listDrivers = driversService.listAll();
        List<Truck> listTrucks = trucksService.listAll();

        // Add truck and driver information to the model
        model.addAttribute("drivers", listDrivers);
        model.addAttribute("trucks", listTrucks);

        // Add an empty Order object to the model for form binding
        model.addAttribute("ordered", new Ordered());

        return "order";
    }

    @PostMapping("/order/saveOrder")
    public String saveOrder(Ordered ordered, Model model, RedirectAttributes ra, @RequestParam("image") MultipartFile multipartFile) throws IOException, UserNotFoundException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        ordered.setPhoto(fileName);
        ordered.getPhotoImagePath();

        // Retrieve the driver and truck entities from the model
        Driver driver = (Driver) model.getAttribute("selectedDriver");
        Truck truck = (Truck) model.getAttribute("selectedTruck");

        // Set the driver and truck entities to the ordered object
        ordered.setDriver(driver);
        ordered.setTruck(truck);

        Ordered save = orderService.save(ordered);

        String uploadDir = "ordered-photo" + save.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        ra.addFlashAttribute("messageOrder", "Order Received.");
        return "redirect:/";
    }
}
