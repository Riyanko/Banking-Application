package com.rio.banking_application.controller;

import java.lang.reflect.AccessFlag.Location;
import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rio.banking_application.dto.AccountDto;
import com.rio.banking_application.service.AccountService;

@RestController
public class AccountController {

	private AccountService accountService;
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	
	@GetMapping("/api/accounts")
	public ResponseEntity<List<AccountDto>> getallaccounts(){
		List<AccountDto> allAccounts = accountService.getAllAccounts();
		return ResponseEntity.ok(allAccounts);
	}
	
	
	@GetMapping("/api/accounts/{id}")
	public ResponseEntity<AccountDto> findAccountById(@PathVariable Long id) {
		AccountDto account = accountService.getAccountById(id);
		return ResponseEntity.ok(account);
	}
	
	
	@PostMapping("/api/accounts")
	public ResponseEntity<AccountDto> createNewAccount(@RequestBody AccountDto accountDto) {
		AccountDto createAccount = accountService.createAccount(accountDto);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createAccount.getId())
				.toUri();
		return ResponseEntity.created(location).body(createAccount);
	}
	
	
	@PutMapping("/api/accounts/{id}/deposit")
	public ResponseEntity<AccountDto> depositAmount(@PathVariable Long id,@RequestBody Map<String, Double> request) {
		
		double amounts=request.get("amount");
		AccountDto accountDto = accountService.deposit(id, amounts);
		return new ResponseEntity<>(accountDto,HttpStatus.OK);
	}
	
	
	@PutMapping(("/api/accounts/{id}/withdraw"))
	public ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id,@RequestBody Map<String, Double> request) {
		double amounts=request.get("amount");
		AccountDto withdraw = accountService.withdraw(id, amounts);
		return ResponseEntity.status(HttpStatus.OK).body(withdraw);
	}
	
	
	@DeleteMapping("/api/accounts/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account is Deleted Successfully!!");
	} 
	
}
