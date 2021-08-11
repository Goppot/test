package spring.boot.security.service;

import org.springframework.web.servlet.ModelAndView;
import spring.boot.security.model.Role;
import spring.boot.security.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    void save(String name, String email, int age, String password, String role);

    Iterable<User> findAll();

    User getById(int id);

    User getShowId();

    void updateUser(int id, String name, String email, int age, String password, String role);

    void removeUser(int id);
}
