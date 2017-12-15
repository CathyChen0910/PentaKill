package com.sf.marathon.pentakill.server.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.marathon.pentakill.server.dao.IGroupDao;
import com.sf.marathon.pentakill.server.domain.Group;
import com.sf.marathon.pentakill.server.service.IGroupService;
import com.sf.marathon.pentakill.server.util.TimeUtil;

@Component
public class GroupServiceImpl extends TransactionalService implements IGroupService {
	@Autowired
	private IGroupDao groupDao;

	@Override
	public List<Group> findAll() {
		return groupDao.findAll();
	}

	@Override
	public List<Group> verify(String marketId, Date date) {
		return groupDao.getBySql(marketId, TimeUtil.now());
	}

	@Override
	public void generate(String marketId) {
		List<Group> list = groupDao.getBySql(marketId, TimeUtil.now());		
		if(list.isEmpty()){
			Group group = list.get(0);
		}
	}

}
