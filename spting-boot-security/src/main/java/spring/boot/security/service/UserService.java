package spring.boot.security.service;

import spring.boot.security.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(String name, String email, int age, String password, String role);

    Iterable<User> findAll();

    User showById(int id);

    void updateUser(int id, String name, String email, int age, String password, String role);

    void removeUser(int id);
}
