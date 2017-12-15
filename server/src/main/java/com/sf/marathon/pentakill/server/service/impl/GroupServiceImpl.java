package com.sf.marathon.pentakill.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public List<Group> existAvailable(String marketId, Date date) {
		return groupDao.getBySql(marketId, TimeUtil.now());
	}

	@Override
	public void generate(String marketId) {
		Group lastGroup = getLast(marketId);
		// 是否超时
		if (lastGroup != null) {
			if (lastGroup.getEndTime().after(TimeUtil.now())) {
				return;
			}

		}
		// 不存在正在报名的集货团或已超时，创建新的group
		Market market = marketDao.findOne(marketId);
		// 由market转生成group
		Group group = new Group();
		group.setGroupType("全国团");
		String periodNum = null;
		if (lastGroup != null) {
			String number = extraNumber(lastGroup.getPeriodNum());
			periodNum = Integer.toString(Integer.parseInt(number) + 1);
		} else {
			periodNum = TimeUtil.getDateYYDD() + "01";

		}
		String periodNumber = market.getMarketName() + periodNum + "期";
		group.setPeriodNum(periodNumber);
		group.setMinBagNum(market.getDailyMinPacks());
		group.setMinPrice(6D);

		group.setEndTime(TimeUtil.add(TimeUtil.now(), market.getGroupPeriod()));
		group.setPromisePeriod(3);
		group.setCreateTime(TimeUtil.now());
		group.setMarketId(marketId);
		groupDao.save(group);
	}

	private String extraNumber(String str) {
		Pattern parttern = Pattern.compile("\\d+");
		Matcher m = parttern.matcher(str);
		return m.find() ? m.group(0) : "";
	}

	private Group getLast(String marketId) {
		Group group = new Group();
		group.setMarketId(marketId);
		Example<Group> example = Example.of(group);
		Sort sort = new Sort(Direction.DESC, "endTime");
		Pageable pageable = new PageRequest(0, 1, sort);
		Page<Group> page = groupDao.findAll(example, pageable);
		if (page.hasContent()) {
			return page.getContent().get(0);
		}
		return null;
	}

}
