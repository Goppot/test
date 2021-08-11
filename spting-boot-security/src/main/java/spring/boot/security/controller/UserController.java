package spring.boot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.boot.security.model.User;
import spring.boot.security.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showUser(Model model) {
        model.addAttribute("user1", userService.getShowId());
        model.addAttribute("user", userService.getShowId());
        return "user";
    }
}
