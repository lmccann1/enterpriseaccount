package com.qa.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;
import com.qa.util.JSONUtil;

import java.util.Collection;

import com.qa.model.Accounts;

@Transactional(SUPPORTS)
public class AccountServiceDBImp {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	@Inject
	private JSONUtil util;
	
	public String getAllAccounts() {
		Query query = em.createQuery("Select a From Account a");
		Collection<Accounts> accounts = (Collection<Accounts>) query.getResultList();
		return util.getJSONForObject(accounts);
		
	}
	@Transactional(REQUIRED)
	public String create(String account) 
	{
	Accounts newAccount = util.getObjectForJSON(account, Accounts.class);
	em.persist(newAccount);
	return "{\"message\": \"account has been sucessfully added\"}";
	}
	
	@Transactional(REQUIRED)
	public String updateAccount(Long id, String accountUpdate) {
		Accounts updatedAccount = util.getObjectForJSON(accountUpdate, Accounts.class);
		Accounts accountDB = findAccount(id);
		if(accountUpdate != null) {
			accountDB = updatedAccount;
			em.merge(accountDB);
		}
		return "{\"message\": \"account sucessfully updated\"}";
	}
	@Transactional(REQUIRED)
	public String delAccount(Long id, String accountDel) {
		Accounts deletedAccount = util.getObjectForJSON(accountDel, Accounts.class);
		Accounts accountInDB = findAccount(id);
		if(accountInDB != null) {
			
			em.remove(accountInDB);
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}

	
	public Accounts findAccount(Long id) {
		return em.find(Accounts.class, id);
	}
	
	public void setManager(EntityManager em) {
		this.em = em;
	}
	public void setUtil(JSONUtil util) {
		this.util = util;
		
	}
	
	}

