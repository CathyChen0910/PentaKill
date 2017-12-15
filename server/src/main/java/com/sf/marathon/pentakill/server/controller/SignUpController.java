package com.sf.marathon.pentakill.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sf.marathon.pentakill.server.controller.dto.req.SignUpReq;
import com.sf.marathon.pentakill.server.service.ISignUpService;

@RestController
@RequestMapping("signUp")
public class SignUpController extends BaseController {

	@Autowired
	private ISignUpService signUpService;

	@RequestMapping(method = RequestMethod.POST)
	public RestResponse<Boolean> save(@RequestBody SignUpReq signUpReq) {
		return handle(restResponse -> {
			boolean result = signUpService.signUp(signUpReq);
			restResponse.setResult(result);
		});
	}
}
