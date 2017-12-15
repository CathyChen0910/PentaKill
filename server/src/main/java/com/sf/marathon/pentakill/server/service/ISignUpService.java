package com.sf.marathon.pentakill.server.service;

import com.sf.marathon.pentakill.server.controller.dto.req.SignUpReq;

public interface ISignUpService {
	public Integer findCount(Long id);

	public boolean signUp(SignUpReq signUpReq);
}
