package manakin.ru.stalcraftmonitor.controller;

import jakarta.servlet.http.HttpSession;
import manakin.ru.stalcraftmonitor.dto.UserDto;
import manakin.ru.stalcraftmonitor.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(@Autowired Model model) {
        model.addAttribute("userDto", new UserDto("", "", ""));
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistration(
            @Validated @ModelAttribute("userDto") UserDto userDto,
            BindingResult bindingResult,
//            @ModelAttribute("username") String username,
//            @ModelAttribute("email") String email,
//            @ModelAttribute("password") String password,
            @ModelAttribute("retypedPassword") String retypedPassword,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "processRegistration";
        }

        if (!userDto.password().equals(retypedPassword)) {
            model.addAttribute("passwordsNotEquals", true);
            return "processRegistration";
        }

        try {
            userService.registerUser(userDto.userName(), userDto.email(), userDto.password());
        } catch (DuplicateKeyException e) {
            return e.getMessage();
        }

        model.addAttribute(userDto);

        return "processRegistration";
    }
}
