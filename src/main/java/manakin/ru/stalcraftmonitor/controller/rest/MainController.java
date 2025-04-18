package manakin.ru.stalcraftmonitor.controller.rest;

import manakin.ru.stalcraftmonitor.entity.UserEntity;
import manakin.ru.stalcraftmonitor.service.UserService;
import manakin.ru.stalcraftmonitor.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {

    private final UserServiceImpl userService;

    @Autowired
    public MainController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @ModelAttribute("username") String username,
            @ModelAttribute("email") String email,
            @ModelAttribute("password") String password
    ) {
        userService.registerUser(username, email, password);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User with username: " + username + " registered");
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }
}
