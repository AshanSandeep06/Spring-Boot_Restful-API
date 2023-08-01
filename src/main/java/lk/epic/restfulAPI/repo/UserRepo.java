package lk.epic.restfulAPI.repo;

import lk.epic.restfulAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
    User findByEmail(String email);
    User findUserByEmailAndPassword(String email, String password);
}
