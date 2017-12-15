package com.sf.marathon.pentakill.server.service;

import java.util.List;

import com.sf.marathon.pentakill.server.domain.Market;

public interface IMarketService {

	public Market getById(String id);

	public List<String> getNeedToGenerate();

}
