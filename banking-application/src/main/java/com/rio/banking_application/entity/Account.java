package com.rio.banking_application.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="accounts")
@Entity
public class Account {
	@Id
	//@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Account_Holder_Name")
	private String accountHolderName;
	
	@Column(name="Account_Balance")
	private double balance;
}
