package com.liyang.domain.role;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.liyang.domain.base.StateRepository;

/**
 * @author Administrator
 *
 */
// @RepositoryRestResource(excerptProjection = MenuProjection.class)
public interface RoleStateRepository extends StateRepository<RoleState> {

	@Query("select  c.label  from RoleState c ")
	public List<RoleState> findAllStateCode();
}
