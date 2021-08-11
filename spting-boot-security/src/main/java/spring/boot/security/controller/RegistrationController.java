package spring.boot.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.boot.security.model.Role;
import spring.boot.security.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public String addUserForm() {
        return "/registration";
    }

    @PostMapping()
    public String addUser(@RequestParam String name,
                          @RequestParam int age,
                          @RequestParam String email,
                          @RequestParam String password,
                          @RequestParam String role,
                          Model model) {
        userService.save(name, email, age, password, role);
        return "redirect:/login";
    }
}
