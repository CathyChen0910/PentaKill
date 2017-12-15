package com.sf.marathon.pentakill.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sf.marathon.pentakill.server.domain.Group;
import com.sf.marathon.pentakill.server.service.IGroupService;

@RestController
@RequestMapping("/group")
public class GroupController extends BaseController{
	@Autowired
	private IGroupService groupService;
	
	@GetMapping("/get")
	public RestResponse<List<Group>> getAll() {
		return handle(response -> response.setResult(groupService.findAll()));
	}
	
}
