package com.sf.marathon.pentakill.server.task;

import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.sf.marathon.pentakill.server.service.IGroupService;

@Component
public class GenerateGroupTask {

	private static final Log log = LogFactory.getLog(GenerateGroupTask.class);

	@Autowired
	private IGroupService groupService;

	@Async("mySimpleAsync")
	public Future<Boolean> generate(String marketId) {
		// lock marketId
		groupService.generate(marketId);
		// unlock
		return new AsyncResult<>(true);
	}

}
