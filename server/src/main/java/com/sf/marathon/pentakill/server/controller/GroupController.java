package com.sf.marathon.pentakill.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sf.marathon.pentakill.server.domain.Group;
import com.sf.marathon.pentakill.server.domain.Market;
import com.sf.marathon.pentakill.server.service.IGroupService;
import com.sf.marathon.pentakill.server.service.IMarketService;
import com.sf.marathon.pentakill.server.service.ISignUpService;

@RestController
@RequestMapping("/group")
public class GroupController extends BaseController{
	@Autowired
	private IGroupService groupService;
	
	@Autowired
	private IMarketService marketService;
	
	@Autowired
	private ISignUpService signUpService;
	
	@GetMapping("/get")
	public RestResponse<List<Group>> getAll() {
		return handle(response -> response.setResult(groupService.findAll()));
	}
	
	@GetMapping("/getOne/{id}")
	public RestResponse<Group> getOne(@PathVariable(value = "id") String id){
		return handle(response -> response.setResult(groupService.getOne(Long.valueOf(id))));
	}
	
	@GetMapping("/getOne/market/{id}")
	public RestResponse<Market> getMarketByGroup(@PathVariable(value = "id") String id){
		return handle(response -> response.setResult(marketService.getById(id)));
	}
	
	@GetMapping("/getOne/getAllUser/{id}")
	public RestResponse<Integer> getGroupPersonAll(@PathVariable(value = "id") String id){
		return handle(response -> response.setResult(signUpService.findCount(Long.valueOf(id))));
	}
	
}