package com.sf.marathon.pentakill.server.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sf.marathon.pentakill.server.domain.Group;

public interface IGroupDao extends JpaRepository<Group, Long> {

	@Query(value = "SELECT * from shipping_group where MARKET_ID =:marketId and END_TIME > :date ", nativeQuery = true)
	public List<Group> getBySql(@Param("marketId") String marketId, @Param("date") Date date);

}
