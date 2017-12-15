package com.sf.marathon.pentakill.server.task;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.sf.marathon.pentakill.server.service.IGroupService;
import com.sf.marathon.pentakill.server.util.LockUtil;

@Component
public class GenerateGroupTask {

	private static final String PREFIX = "_generategroup_";

	@Autowired
	private IGroupService groupService;

	@Async("mySimpleAsync")
	public Future<Boolean> generate(String marketId) {
		String key = PREFIX + marketId;
		LockUtil.lock(key);
		try {
			groupService.generate(key);
			return new AsyncResult<>(true);
		} finally {
			LockUtil.unLock(key);
		}
	}

}
