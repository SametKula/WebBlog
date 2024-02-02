package com.sametkula.webBlog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {
    private UUID id;
    private String username;
    private String email;
    private boolean isEmailEnabled;
    private Integer totalPost;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private byte[] profilePicture;
    private String roles;
}
