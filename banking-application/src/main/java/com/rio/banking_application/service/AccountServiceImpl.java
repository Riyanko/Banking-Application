package com.rio.banking_application.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rio.banking_application.dto.AccountDto;
import com.rio.banking_application.entity.Account;
import com.rio.banking_application.mapper.AccountMapper;
import com.rio.banking_application.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	private AccountRepository accrepo;
	public AccountServiceImpl(AccountRepository accrepo) {
		this.accrepo = accrepo;
	}


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account=AccountMapper.mapToAccount(accountDto);
		Account savedAccount=accrepo.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts=accrepo.findAll();
		return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
		
	}


	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accrepo
				.findById(id)
				.orElseThrow(()-> new RuntimeException("Account doesnt exists") );
		
		return AccountMapper.mapToAccountDto(account);
	}


	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accrepo
				.findById(id)
				.orElseThrow(()-> new RuntimeException("Account doesnt exists") );
		double sum=account.getBalance()+amount;
		account.setBalance(sum);
		Account savedAccount = accrepo.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

}
