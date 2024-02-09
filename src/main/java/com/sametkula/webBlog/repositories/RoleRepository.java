package com.sametkula.webBlog.repositories;

import com.sametkula.webBlog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByRoleName(String roleName);
}
