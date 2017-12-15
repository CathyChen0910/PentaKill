package com.sf.marathon.pentakill.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.marathon.pentakill.server.dao.ISignUpDao;
import com.sf.marathon.pentakill.server.service.ISignUpService;

@Component
public class SignUpServiceImpl extends TransactionalService implements ISignUpService{
	
	@Autowired
	private ISignUpDao signUpDao;

	@Override
	public Integer findCount(Long id) {
		return  signUpDao.findCount(id);
	}
	
}
