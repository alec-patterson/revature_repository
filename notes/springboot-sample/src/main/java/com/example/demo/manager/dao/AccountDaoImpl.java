package com.example.demo.manager.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Account;


//repository makes a bean
@Repository
public class AccountDaoImpl implements AccountDao{

		@Override
		public List<Account> findAll() {
			List<Account> accounts = new ArrayList<Account>();
			accounts.add(new Account("admin"));
			return accounts;
		}
	
}
