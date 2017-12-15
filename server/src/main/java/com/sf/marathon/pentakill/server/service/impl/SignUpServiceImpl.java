package com.sf.marathon.pentakill.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.marathon.pentakill.server.controller.dto.req.SignUpReq;
import com.sf.marathon.pentakill.server.dao.ISignUpDao;
import com.sf.marathon.pentakill.server.domain.Group;
import com.sf.marathon.pentakill.server.domain.Market;
import com.sf.marathon.pentakill.server.domain.SignUp;
import com.sf.marathon.pentakill.server.service.IGroupService;
import com.sf.marathon.pentakill.server.service.IMarketService;
import com.sf.marathon.pentakill.server.service.ISignUpService;
import com.sf.marathon.pentakill.server.task.GenerateGroupTask;
import com.sf.marathon.pentakill.server.util.BeanUtil;
import com.sf.marathon.pentakill.server.util.TimeUtil;

@Component
public class SignUpServiceImpl extends TransactionalService implements ISignUpService {

	@Autowired
	private ISignUpDao signUpDao;
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IMarketService marketService;
	@Autowired
	private GenerateGroupTask generateGroupTask;

	@Override
	public Integer findCount(Long id) {
		return signUpDao.findCount(id);
	}

	@Override
	public boolean signUp(SignUpReq signUpReq) {
		Long groupId = signUpReq.getGroupId();
		Group group = groupService.getOne(groupId);
		Market market = marketService.getById(group.getMarketId());
		int limitNum = market.getLimitNum();

		if (group.getEndTime().before(TimeUtil.now())) {
			return false;
		}
		Integer haveSignUpNum = findCount(groupId);
		if (haveSignUpNum >= limitNum) {
			return false;
		}

		SignUp signUp = BeanUtil.copyProperties(signUpReq, SignUp.class);
		signUp.setCreateTime(TimeUtil.now());
		signUpDao.save(signUp);
		if (haveSignUpNum + 1 >= limitNum) {
			generateGroupTask.generate(market.getId());
		}
		return true;
	}

}
