package com.sametkula.webBlog.services;

import com.sametkula.webBlog.dto.CreateAccountRequest;
import com.sametkula.webBlog.dto.UpdateAccountRequest;
import com.sametkula.webBlog.model.Account;
import com.sametkula.webBlog.model.AccountPicture;
import com.sametkula.webBlog.repositories.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public Account loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findAccountByUsername(username);
    }

    //Crud methods

    public Account getAccountById(UUID uuid) {
        return accountRepository.findById(uuid).orElseThrow(RuntimeException::new);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    public Account createAccount(CreateAccountRequest req) {

        Account res = Account.builder()
                .username(req.getUsername())
                .password(req.getPassword())
                .email(req.getEmail())
                .build();

        return accountRepository.save(res);
    }
    public Account updateAccount(UpdateAccountRequest req) {

        UUID pictureId = accountRepository.findById(req.getId()).orElseThrow(RuntimeException::new).getAccountPicture().getId();

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
                .accountPicture(
                        AccountPicture.builder()
                                .id(pictureId)
                                .picture(req.getProfilePicture())
                                .build()
                )
                .totalPost(req.getTotalPost())
                .build();

        return accountRepository.save(res);
    }

    public void deleteAccount(UUID uuid) {
        accountRepository.deleteById(uuid);
    }



}
