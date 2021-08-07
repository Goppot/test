package spring.boot.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.boot.security.model.User;
import spring.boot.security.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String user(Model model) {
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "/all-users";
    }

    @GetMapping("user/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.showById(id));
        return "/user";
    }

    @GetMapping("/create-user")
    public String addUserForm() {
        return "/create-user";
    }

    @PostMapping("create-user")
    public String addUser(@RequestParam String name,
                          @RequestParam int age,
                          @RequestParam String email,
                          @RequestParam String password,
                          @RequestParam String role,
                          Model model) {
        userService.save(name, email, age, password, role);
        return "redirect:/admin";
    }

    @GetMapping("/update-user/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.showById(id));
        return "/update-user";
    }

    @PatchMapping("update-user")
    public String updateUser(@RequestParam int id,
                             @RequestParam String name,
                             @RequestParam int age,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String role) {
        userService.updateUser(id, name, email, age, password, role);
        return "redirect:/admin";
    }

    @PostMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }








}
