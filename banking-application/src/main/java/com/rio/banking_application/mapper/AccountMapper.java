package com.rio.banking_application.mapper;

import com.rio.banking_application.dto.AccountDto;
import com.rio.banking_application.entity.Account;

public class AccountMapper {
	public static Account mapToAccount(AccountDto accountDto) {
		Account account=new Account(
				accountDto.getId(),
				accountDto.getAccountHoldername(),
				accountDto.getBalance()
		);
		return account;
	}
	public static AccountDto mapToAccountDto(Account account) {
		AccountDto accountDto=new AccountDto(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
			);
		return accountDto;
	}
}
