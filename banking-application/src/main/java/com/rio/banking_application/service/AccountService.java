package com.rio.banking_application.service;

import java.util.List;

import com.rio.banking_application.dto.AccountDto;

public interface AccountService {
	AccountDto createAccount(AccountDto account);
	List<AccountDto> getAllAccounts();
	AccountDto getAccountById(Long id);
	AccountDto deposit(Long id, double amount);
	AccountDto withdraw(Long id, double amount);
}
