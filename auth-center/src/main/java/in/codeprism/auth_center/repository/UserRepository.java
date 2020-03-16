package in.codeprism.auth_center.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.codeprism.auth_center.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
