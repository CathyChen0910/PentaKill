package com.sf.marathon.pentakill.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sf.marathon.pentakill.server.domain.Market;

public interface IMarketDao extends JpaRepository<Market, Long>{

}
