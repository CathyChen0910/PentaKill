package com.sf.marathon.pentakill.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.marathon.pentakill.server.dao.IMarketDao;
import com.sf.marathon.pentakill.server.domain.Market;
import com.sf.marathon.pentakill.server.service.IMarketService;

@Component
public class MarketServiceImpl extends TransactionalService implements IMarketService {

	@Autowired
	private IMarketDao marketDao;

	@Override
	public Market getById(String id) {
		return marketDao.findOne(id);
	}

}
