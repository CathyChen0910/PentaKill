package com.sf.marathon.pentakill.server.service;

import java.util.Date;
import java.util.List;

import com.sf.marathon.pentakill.server.domain.Group;

public interface IGroupService {
	public List<Group> findAll();

	public List<Group> existAvailable(String marketId, Date date);

	public void generate(String marketId);
}
