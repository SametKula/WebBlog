package com.sametkula.webBlog.services;

import com.sametkula.webBlog.dto.AccountDto;
import com.sametkula.webBlog.dto.AccountDtoConverter;
import com.sametkula.webBlog.dto.CreateAccountRequest;
import com.sametkula.webBlog.dto.UpdateAccountRequest;
import com.sametkula.webBlog.model.Account;
import com.sametkula.webBlog.model.AccountPicture;
import com.sametkula.webBlog.model.Role;
import com.sametkula.webBlog.repositories.AccountRepository;
import com.sametkula.webBlog.security.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AccountDtoConverter accountDtoConverter;

    public AccountService(AccountRepository accountRepository, RoleService roleService, PasswordEncoder passwordEncoder, AccountDtoConverter accountDtoConverter) {
        this.accountRepository = accountRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.accountDtoConverter = accountDtoConverter;
    }


    @Override
    public Account loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findAccountByUsername(username);
    }

    //Crud methods

    public Account getAccountById(UUID uuid) {
        return accountRepository.findById(uuid).orElseThrow(RuntimeException::new);
    }

    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(accountDtoConverter::convertToDto)
                .collect(Collectors.toList());
    }
    public Account createAccount(CreateAccountRequest req) throws Exception {

        var username = accountRepository.findAccountByUsername(req.getUsername());
        if (username != null) {
            throw new Exception("Username already exists");
        }

        var userRole = Role.builder()
                .roleName("user")
                .build();
        Account res = Account.builder()
                .id(UUID.randomUUID())
                .username(req.getUsername())
                .password(passwordEncoder.bCryptPasswordEncoder().encode(req.getPassword()))
                .email(req.getEmail())
                .roles(List.of(userRole))
                .accountPicture(AccountPicture.builder().build())
                .build();


        return accountRepository.save(res);
    }
    public Account updateAccount(UpdateAccountRequest req) {


        Account res = Account.builder()
                .id(req.getId())
                .email(req.getEmail())
                .password(req.getPassword())
                .username(req.getUsername())
                .creationDate(req.getCreationDate())
                .isAccountNonExpired(req.isAccountNonExpired())
                .isAccountNonLocked(req.isAccountNonLocked())
                .isCredentialsNonExpired(req.isCredentialsNonExpired())
                .isEnabled(req.isEnabled())
                .isEmailEnabled(req.isEmailEnabled())
                .totalPost(req.getTotalPost())
                .build();

        return accountRepository.save(res);
    }

    public void deleteAccount(UUID uuid) {
        accountRepository.deleteById(uuid);
    }



}
