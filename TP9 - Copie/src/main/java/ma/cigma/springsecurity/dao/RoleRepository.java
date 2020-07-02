package ma.cigma.springsecurity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.cigma.springsecurity.service.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	List<Role> findByRole(String role);
	List<Role> findAll();
	@Query("select r from Role r where r.role != :role")
	List<Role> getAllRolesOtherThenSuperAdmin(@Param("role") String role);

}