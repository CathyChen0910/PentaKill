package com.sf.marathon.pentakill.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sf.marathon.pentakill.server.controller.dto.req.TestReq;
import com.sf.marathon.pentakill.server.controller.dto.resp.TestResp;
import com.sf.marathon.pentakill.server.domain.Market;
import com.sf.marathon.pentakill.server.domain.Test;
import com.sf.marathon.pentakill.server.service.ITtestService;
import com.sf.marathon.pentakill.server.util.BeanUtil;

@RestController
@RequestMapping("test")
public class TestController extends BaseController {
	@Autowired
	private ITtestService testService;

	@RequestMapping(method = RequestMethod.POST)
	public RestResponse<Test> save(@RequestBody TestReq testReq) {
		return handle(restResponse -> {
			Test test = BeanUtil.copyProperties(testReq, Test.class);
			test = testService.save(test);
			restResponse.setResult(test);
		});
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RestResponse<TestResp> getById(@PathVariable(value = "id") Long id) {
		return handle(restResponse -> {
			Test test = testService.getById(id);
			restResponse.setResult(BeanUtil.copyProperties(test, TestResp.class));
		});

	}
	
	@GetMapping(value="/123")
	public RestResponse<List<Market>> getMarkets(){
		return handle(response -> {
			List<Market> allMarkets = testService.findAllMarket();
			response.setResult(allMarkets);
		});
	}
	
}
