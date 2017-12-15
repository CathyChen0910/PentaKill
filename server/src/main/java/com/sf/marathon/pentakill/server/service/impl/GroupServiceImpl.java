package com.sf.marathon.pentakill.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.marathon.pentakill.server.dao.IGroupDao;
import com.sf.marathon.pentakill.server.domain.Group;
import com.sf.marathon.pentakill.server.service.IGroupService;

@Component
public class GroupServiceImpl extends TransactionalService implements IGroupService{
	@Autowired
	private IGroupDao groupDao;

	@Override
	public List<Group> findAll() {
		return groupDao.findAll();
	}

}
