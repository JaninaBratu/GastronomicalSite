package ro.ace.ucv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.ace.ucv.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);
}
