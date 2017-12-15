package com.sf.marathon.pentakill.server.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

	private static final Log log = LogFactory.getLog(ScheduledTask.class);

	@Autowired
	private GenerateGroupTask generateGroupTask;

	@Scheduled(cron = "0 0/1 * * * ?")
	public void executeFileDownLoadTask() throws InterruptedException {

		// 间隔2分钟,执行工单上传任务
		Thread current = Thread.currentThread();
		System.out.println("定时任务1:" + current.getId());
		log.info("ScheduledTest.executeFileDownLoadTask 定时任务1:" + current.getId() + ",name:" + current.getName());

		generateGroupTask.generate("");
	}
}
