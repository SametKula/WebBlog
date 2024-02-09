package com.sametkula.webBlog.dto;

import com.sametkula.webBlog.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountRequest {
    private UUID id;
    private String username;
    private String password;
    private Date creationDate;
    private String email;
    private boolean isEmailEnabled;
    private Integer totalPost;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private Set<Role> roles;
}