package com.gyh.system.job.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.gyh.common.constant.Constant;
import com.gyh.common.exception.RRException;
import com.gyh.system.job.entity.ScheduleJob;
import org.quartz.*;

/**
 * @author guoyh
 * @Date 2019/5/27 22:50
 */
public class ScheduleUtils {

    private final static String JOB_NAME = "TASK_";


    /**
     * 获取触发器key
     */
    public static TriggerKey getTriggerKey(String id) {
        return TriggerKey.triggerKey(JOB_NAME + id);
    }

    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(String id) {
        return JobKey.jobKey(JOB_NAME + id);
    }


    /**
     * 获取表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, String id) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(id));
        } catch (SchedulerException e) {
            throw new RRException("获取定时任务CronTrigger出现异常", e);
        }
    }

    /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, ScheduleJob scheduleJob) {
        try {
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJobExecuter.class).withIdentity(getJobKey(scheduleJob.getId())).build();

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(scheduleJob.getId())).withSchedule(scheduleBuilder).build();

            //放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(ScheduleJob.JOB_PARAM_KEY, JSONObject.toJSONString(scheduleJob));

            scheduler.scheduleJob(jobDetail, trigger);

            //暂停任务
            if(scheduleJob.getStatus() == Constant.ScheduleStatus.PAUSE.getValue()){
                pauseJob(scheduler, scheduleJob.getId());
            }
        } catch (SchedulerException e) {
            throw new RRException("创建定时任务失败", e);
        }
    }


    /**
     * 更新定时任务
     */
    public static void updateScheduleJob(Scheduler scheduler, ScheduleJob scheduleJob) {
        try {
            TriggerKey triggerKey = getTriggerKey(scheduleJob.getId());

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();

            CronTrigger trigger = getCronTrigger(scheduler, scheduleJob.getId());

            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            //参数
            trigger.getJobDataMap().put(ScheduleJob.JOB_PARAM_KEY, JSONObject.toJSONString(scheduleJob));

            scheduler.rescheduleJob(triggerKey, trigger);

            //暂停任务
            if(scheduleJob.getStatus() == Constant.ScheduleStatus.PAUSE.getValue()){
                pauseJob(scheduler, scheduleJob.getId());
            }

        } catch (SchedulerException e) {
            throw new RRException("更新定时任务失败", e);
        }
    }


    /**
     * 启动任务
     */
    public static void runJob(Scheduler scheduler, ScheduleJob scheduleJob) {
        try {
            //参数
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(ScheduleJob.JOB_PARAM_KEY, JSONObject.toJSONString(scheduleJob));
            scheduler.triggerJob(getJobKey(scheduleJob.getId()), dataMap);
        } catch (SchedulerException e) {
            throw new RRException("立即执行定时任务失败", e);
        }
    }



    /**
     * 暂停任务
     */
    public static void pauseJob(Scheduler scheduler, String id) {
        try {
            scheduler.pauseJob(getJobKey(id));
        } catch (SchedulerException e) {
            throw new RRException("暂停定时任务失败", e);
        }
    }


    /**
     * 恢复任务
     */
    public static void resumeJob(Scheduler scheduler, String id) {
        try {
            scheduler.resumeJob(getJobKey(id));
        } catch (SchedulerException e) {
            throw new RRException("暂停定时任务失败", e);
        }
    }


    /**
     * 删除定时任务
     */
    public static void deleteScheduleJob(Scheduler scheduler, String id) {
        try {
            scheduler.deleteJob(getJobKey(id));
        } catch (SchedulerException e) {
            throw new RRException("删除定时任务失败", e);
        }
    }


}
