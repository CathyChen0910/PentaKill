package com.sf.marathon.pentakill.server.task;

import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.sf.marathon.pentakill.server.domain.Market;
import com.sf.marathon.pentakill.server.service.IMarketService;

@Component
public class GenerateGroupTask {

	private static final Log log = LogFactory.getLog(GenerateGroupTask.class);

	@Autowired
	private IMarketService marketService;

	@Async("mySimpleAsync")
	public Future<String> generate(String marketId) throws InterruptedException {
		// lock marketId
		Market byId = marketService.getById(marketId);
		// unlock
		return new AsyncResult<>("Task1 accomplished!");
	}

}
