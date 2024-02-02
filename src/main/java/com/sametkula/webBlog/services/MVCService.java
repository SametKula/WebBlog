package com.sametkula.webBlog.services;

import com.sametkula.webBlog.dto.AccountDto;
import com.sametkula.webBlog.dto.AccountDtoConverter;
import com.sametkula.webBlog.dto.CreateAccountRequest;
import com.sametkula.webBlog.dto.UpdateAccountRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MVCService {

    private final AccountService accountService;
    private final AccountDtoConverter accountDtoConverter;

    public MVCService(AccountService accountService, AccountDtoConverter accountDtoConverter) {
        this.accountService = accountService;
        this.accountDtoConverter = accountDtoConverter;
    }

    public List<AccountDto> getAllUsers() {
        return accountService.getAllAccounts().stream()
                .map(accountDtoConverter::convertToDto)
                .collect(Collectors.toList());
    }
    public AccountDto getUserById(String id) {
        return accountDtoConverter.convertToDto(accountService.getAccountById(UUID.fromString(id)));
    }
    public AccountDto createUser(CreateAccountRequest req) {
        return accountDtoConverter.convertToDto(accountService.createAccount(req));
    }
    public AccountDto updateUser(UpdateAccountRequest req) {
        return accountDtoConverter.convertToDto(accountService.updateAccount(req));
    }
    public void deleteUser(String id) {
        accountService.deleteAccount(UUID.fromString(id));
    }
}
