package com.sf.marathon.pentakill.server.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sf.marathon.pentakill.server.domain.Market;

public interface IMarketDao extends JpaRepository<Market, String> {
	@Query(value = "SELECT a.MKT_ID from pro_market_base a WHERE not EXISTS( SELECT 1 from shipping_group where MARKET_ID =a.MKT_ID and END_TIME > :date);", nativeQuery = true)
	public List<String> getBySql(@Param("date") Date date);

}
