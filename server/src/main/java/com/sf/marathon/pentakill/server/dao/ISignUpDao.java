package com.sf.marathon.pentakill.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sf.marathon.pentakill.server.domain.SignUp;

public interface ISignUpDao extends JpaRepository<SignUp, Long>{

}
