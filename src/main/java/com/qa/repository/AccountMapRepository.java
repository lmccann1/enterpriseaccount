package com.qa.repository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.qa.model.Accounts;
import com.qa.util.JSONUtil;



public class AccountMapRepository implements AccountRepository {
	
	private Map<Long, Accounts> accountsMap;

	@Inject
private JSONUtil util;

private Long counter = 1L;
	
	public AccountMapRepository() {
	this.accountsMap = new HashMap<Long, Accounts>();
	}
	
	public String getAllAccounts() {
	return util.getJSONForObject(accountsMap);
	
}


@Override
public String create(String account) {
	
	counter++;
	accountsMap.put(counter, util.getObjectForJSON(account, Accounts.class) );
	return account;
	
}

@Override
public String updateAccount(Long id, String accountUpdate) {
	accountsMap.put(counter, util.getObjectForJSON(accountUpdate, Accounts.class) );
	return accountUpdate;
}

@Override
public String delAccount(Long id, String accountDel) {
	accountsMap.remove(id);
	return "{\"message\": \"accout sucessfully removed\"}";
}
	
	
	
	
	
	
	
	
	
	
	
}
