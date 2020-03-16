package in.codeprism.auth_center.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.codeprism.auth_center.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
