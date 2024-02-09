package com.sametkula.webBlog.services;

import com.sametkula.webBlog.model.Role;
import com.sametkula.webBlog.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    protected Role findRoleById(int id) throws Exception {
        return repository.findById(id).orElseThrow(Exception::new);
    }
    protected Role findRoleByRoleName(String roleName) {
        return repository.findRoleByRoleName(roleName);
    }
}
