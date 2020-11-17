package com.gyh.system.job.utils;

import com.alibaba.fastjson.JSONObject;
import com.gyh.common.utils.SpringContext;
import com.gyh.config.executor.ExecutorConfig;
import com.gyh.system.job.entity.ScheduleJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 定时任务  异常
 * @author guoyh
 * @Date 2019/5/27 22:58
 */
public class ScheduleJobExecuter extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private ExecutorService service = ExecutorConfig.buildThreadPool();

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String jsonJob = context.getMergedJobDataMap().getString(ScheduleJob.JOB_PARAM_KEY);
        ScheduleJob scheduleJob = JSONObject.parseObject(jsonJob, ScheduleJob.class);

        //任务开始时间
        long startTime = System.currentTimeMillis();

        ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(),scheduleJob.getMethodName(), scheduleJob.getParams());

        service.execute(task);

        //任务执行总时长
        long times = System.currentTimeMillis() - startTime;
//        logger.debug("任务执行完毕，任务ID：" + scheduleJob.getId() + "任务NAME: " + scheduleJob.getJobName() + "  总共耗时：" + times/1000 + "秒");
    }
}
