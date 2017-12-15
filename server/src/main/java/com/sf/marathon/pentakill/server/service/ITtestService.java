package com.sf.marathon.pentakill.server.service;

import java.util.List;

import com.sf.marathon.pentakill.server.domain.Market;
import com.sf.marathon.pentakill.server.domain.Test;

public interface ITtestService {

	public Test getById(Long id);

	public Test save(Test test);
	
	public List<Market> findAllMarket();
}
