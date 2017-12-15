package com.sf.marathon.pentakill.server.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sf.marathon.pentakill.server.controller.BaseController;

@Component
public class ScheduledTask {

	private static final Log log = LogFactory.getLog(BaseController.class);

	@Scheduled(cron = "0 0/1 * * * ?")
	public void executeFileDownLoadTask() {

		// 间隔2分钟,执行工单上传任务
		Thread current = Thread.currentThread();
		System.out.println("定时任务1:" + current.getId());
		log.info("ScheduledTest.executeFileDownLoadTask 定时任务1:" + current.getId() + ",name:" + current.getName());
	}
}
