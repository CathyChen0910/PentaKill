package com.sf.marathon.pentakill.server.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.sf.marathon.pentakill.server.dao.IGroupDao;
import com.sf.marathon.pentakill.server.dao.IMarketDao;
import com.sf.marathon.pentakill.server.domain.Group;
import com.sf.marathon.pentakill.server.domain.Market;
import com.sf.marathon.pentakill.server.service.IGroupService;
import com.sf.marathon.pentakill.server.util.TimeUtil;

@Component
public class GroupServiceImpl extends TransactionalService implements IGroupService {
	@Autowired
	private IGroupDao groupDao;
	@Autowired
	private IMarketDao marketDao;

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
		// 不存在正在报名的集货团
		if (list.isEmpty()) {
			Market market = marketDao.findOne(marketId);
			// 由market转生成group
			Group group = new Group();
			group.setGroupType("全国团");
			// todo generatecode
			String periodNumber = market.getMarketName() + "120601" + "期";
			group.setPeriodNum(periodNumber);
			group.setMinBagNum(market.getDailyMinPacks());

		}
	}

	public Group getLast(String marketId) {
		Group group = new Group();
		group.setMarketId(marketId);
		Example<Group> example = Example.of(group);
		Sort sort = new Sort(Direction.ASC, "createDate", "id");
		Pageable pageable = new PageRequest(0, 1, sort);
		Page<Group> page = groupDao.findAll(example, pageable);
		if (page.hasContent()) {
			return page.getContent().get(0);
		}
		return null;
	}

	@Override
	public Group getOne(Long id) {
		return groupDao.findOne(id);
	}

}
