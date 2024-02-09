package com.sametkula.webBlog.dto;

import com.sametkula.webBlog.model.Account;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AccountDtoConverter {

    public AccountDto convertToDto(Account from) {
        return AccountDto.builder()
                .id(from.getId())
                .username(from.getUsername())
                .email(from.getEmail())
                .isAccountNonExpired(from.isAccountNonExpired())
                .isAccountNonLocked(from.isAccountNonLocked())
                .isCredentialsNonExpired(from.isCredentialsNonExpired())
                .isEnabled(from.isEnabled())
                .isEmailEnabled(from.isEmailEnabled())
                .totalPost(from.getTotalPost())
                .build();
    }
}
