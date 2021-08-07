package spring.boot.security.repository;

import org.springframework.data.repository.CrudRepository;
import spring.boot.security.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);
}
