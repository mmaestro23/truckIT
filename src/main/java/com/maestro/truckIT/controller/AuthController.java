package com.maestro.truckIT.controller;

import com.maestro.truckIT.model.Driver;
import com.maestro.truckIT.model.Ordered;
import com.maestro.truckIT.model.Truck;
import com.maestro.truckIT.model.Users;
import com.maestro.truckIT.repo.OrderRepo;
import com.maestro.truckIT.service.*;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class AuthController {

    private UsersService service;

    private DriversService driversService;

    private TrucksService trucksService;

    private OrderService orderService;

    private JavaMailSender mailSender;

    private OrderRepo orderRepo;

    @Autowired
    public AuthController(UsersService service, DriversService driversService, TrucksService trucksService, OrderService orderService, JavaMailSender mailSender, OrderRepo orderRepo) {
        this.service = service;
        this.driversService = driversService;
        this.trucksService = trucksService;
        this.orderService = orderService;
        this.mailSender = mailSender;
        this.orderRepo = orderRepo;
    }

    @GetMapping("/")
    public String showData(Model model) {
        List<Driver> listDrivers = driversService.listAll();
        List<Truck> listTrucks = trucksService.listAll();
        model.addAttribute("listDrivers", listDrivers);
        model.addAttribute("listTrucks", listTrucks);
        return "index";
    }

    @GetMapping( "/dash")
    public String showDataOnDash(Model model) {
        List<Users> listUsers = service.listAll();
        List<Driver> listDrivers = driversService.listAll();
        List<Truck> listTrucks = trucksService.listAll();
        List<Ordered> listOrders = orderService.listAll();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("listDrivers", listDrivers);
        model.addAttribute("listTrucks", listTrucks);
        model.addAttribute("listOrders", listOrders);

        return "dash";
    }



    @GetMapping("/register")
    public String showNewForm(Model model){
        model.addAttribute("users", new Users());
        //model.addAttribute("pageTitle", "Add a New Customer");
        return "register";
    }

    @PostMapping("/register/saveUser")
    public String saveUser(@ModelAttribute("user") Users user){

        Users existingUserEmail = UsersService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()){
            return "redirect:/register?fail";
        }
        Users existingUserUsername = UsersService.findByUsername(user.getUsername());
        if(existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()){
            return "redirect:/register?fail";
        }

        service.saveUser(user);
        return "redirect:/index?success";
    }

    /*@PostMapping("/saveUser")
    public String saveUserNew(Users users){

        service.save(users);
        return "redirect:/login";
    }*/

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("users", new Users());
        return "login";
    }

    /*@PostMapping("/login")
    public String login(@ModelAttribute("users") Users users) {
        try {
            Users oauthUser = UsersService.users(users.getEmail(), users.getPassword());
            System.out.println(oauthUser);
            if (Objects.nonNull(oauthUser)) {
                return "redirect:/";
            } else {
                return "redirect:/login";
            }
        } catch (Exception e) { TRYING TO SEE!
            System.out.println("Error occurred while logging in: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/login?error=login_error";
        }
    }*/

    @GetMapping("/404")
    public String showErrorPage(){
        return "404";
    }

    @GetMapping("users/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The user with ID " + id + " has been deleted");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/dash";
    }

    @GetMapping("sendMail/{id}")
    public ResponseEntity<Ordered> orderedMail(@PathVariable("id") Integer id) throws URISyntaxException {
        Ordered ordered = orderRepo.findById(id).get();
        sendmail(ordered);
        return ResponseEntity.status(HttpStatus.SEE_OTHER).location(new URI("/success")).build();
    }

    private void sendmail(Ordered ordered){
        final String emailToRecipient = ordered.getEmail();
        final String emailSubject = "truckIT order";
        final String emailMessage = "<html>" +
                "<body style=\"font-family: Arial, sans-serif;\">" +
                "<p>Dear Sir/Madam,</p>" +
                "<p>Thank you for ordering with truckIT. Your delivery is now starting anytime soon.</p>" +
                "<p>Order Details:</p>" +
                "<ul>" +
                "<li><strong>Order ID:</strong> " + ordered.getId() + "</li>" +
                "<li><strong>Customer Name:</strong> " + ordered.getCustomerName() + "</li>" +
                "<li><strong>Delivery Address:</strong> " + ordered.getDeliveryAddress() + "</li>" +
                "<li><strong>Product:</strong> " + ordered.getItem() + "</li>" +
                "</ul>" +
                "<p>truckIT developers, MaeSTRo</p>" +
                "</body>" +
                "</html>";

        mailSender.send(new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(emailToRecipient);
                mimeMessageHelper.setText(emailMessage, true);
                mimeMessageHelper.setSubject(emailSubject);
            }
        });
    }

    @GetMapping("/success")
    public String showSuccessPage(){
        return "success";
    }
}
