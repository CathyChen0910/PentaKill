package com.sf.marathon.pentakill.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.marathon.pentakill.server.dao.ITestDao;
import com.sf.marathon.pentakill.server.domain.Test;
import com.sf.marathon.pentakill.server.service.ITtestService;

@Component
public class TestServiceImpl extends TransactionalService implements ITtestService {

	@Autowired
	private ITestDao testDao;

	@Override
	public Test getById(Long id) {
		return testDao.findOne(id);
	}

	@Override
	public Test save(Test test) {
		return testDao.save(test);
	}

}