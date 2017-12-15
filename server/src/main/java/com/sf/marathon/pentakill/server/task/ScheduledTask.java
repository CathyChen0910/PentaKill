package com.sf.marathon.pentakill.server.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sf.marathon.pentakill.server.service.IMarketService;

@Component
public class ScheduledTask {

	@Autowired
	private GenerateGroupTask generateGroupTask;
	@Autowired
	private IMarketService marketService;

	@Scheduled(cron = "0 0/1 * * * ?")
	public void executeFileDownLoadTask() {
		List<String> list = marketService.getNeedToGenerate();
		list.parallelStream().forEach(markId -> {
			generateGroupTask.generate(markId);

		});

	}
}
