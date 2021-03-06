package com.liyang.domain.account;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Administrator
 *
 */
@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Account save(Account account);
	
	
	public Account getByPhone(String phone);
}
