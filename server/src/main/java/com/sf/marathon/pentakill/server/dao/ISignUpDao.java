package com.sf.marathon.pentakill.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sf.marathon.pentakill.server.domain.SignUp;

public interface ISignUpDao extends JpaRepository<SignUp, Long>{
	@Query(value = "SELECT count(*) from sign_up t where t.SHIPPING_GROUP_ID = :id ", nativeQuery = true)
	public Integer findCount(@Param("id") Long id);
}
