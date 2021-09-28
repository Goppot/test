package spring.boot.security.service;

import spring.boot.security.model.User;

public interface UserService {

    void save(String name, String email, int age, String password, String role);

    Iterable<User> findAll();

    User getById(int id);

    User getAuthorized();

    void updateUser(int id, String name, String email, int age, String password, String role);

    void removeUser(int id);
}
