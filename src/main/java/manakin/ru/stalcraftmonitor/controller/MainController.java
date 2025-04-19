package manakin.ru.stalcraftmonitor.controller;

import jakarta.servlet.http.HttpSession;
import manakin.ru.stalcraftmonitor.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final UserServiceImpl userService;

    @Autowired
    public MainController(
            UserServiceImpl userService
    ) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "registration";
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin() {
        return "home";
    }

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute("username") String username,
            @ModelAttribute("email") String email,
            @ModelAttribute("password") String password,
            @ModelAttribute("retypedPassword") String retypedPassword,
            Model model
    ) {
        if (!retypedPassword.equals(password)) {
            model.addAttribute("passwordsNotEquals", true);
            return "registration";
        }
        try {
            userService.registerUser(username, email, password);
        } catch (DuplicateKeyException e) {
            return e.getMessage();
        }

        return "login";
    }
}
