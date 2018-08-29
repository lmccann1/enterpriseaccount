package com.qa.repository;

public interface AccountRepository {
	
	String getAllAccounts();
	
	String create(String account);
	
	String updateAccount(Long id, String accountUpdate);
	
	String delAccount(Long id, String accountDel);
	
	

}
