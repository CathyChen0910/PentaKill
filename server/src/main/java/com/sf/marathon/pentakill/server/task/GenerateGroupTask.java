package com.sf.marathon.pentakill.server.task;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.sf.marathon.pentakill.server.service.IGroupService;

@Component
public class GenerateGroupTask {

	@Autowired
	private IGroupService groupService;

	@Async("mySimpleAsync")
	public Future<Boolean> generate(String marketId) {
		groupService.generate(marketId);
		return new AsyncResult<>(true);
	}

}
