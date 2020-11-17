package com.gyh.system.job.service;

import com.gyh.common.constant.Constant.*;
import com.gyh.common.persistence.model.Page;
import com.gyh.common.persistence.service.CrudService;
import com.gyh.common.tools.StringUtils;
import com.gyh.system.job.dao.ScheduleJobDao;
import com.gyh.system.job.entity.ScheduleJob;
import com.gyh.system.job.utils.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

/**
 * 定时任务Service
 * @author fyj
 * @version 2017-11-01
 */
@Service
@Transactional(readOnly = true)
public class ScheduleJobService extends CrudService<ScheduleJobDao, ScheduleJob> {

    @Autowired
    @Qualifier("schedulerFactoryBean")
    private Scheduler scheduler;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init(){
        List<ScheduleJob> scheduleJobList = super.findList(new ScheduleJob());
        for(ScheduleJob scheduleJob : scheduleJobList){
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public ScheduleJob get(String id) {
        return super.get(id);
    }

    @Override
    public List<ScheduleJob> findList(ScheduleJob scheduleJob) {
        return super.findList(scheduleJob);
    }

    @Override
    public Page<ScheduleJob> findPage(Page<ScheduleJob> page, ScheduleJob scheduleJob) {
        return super.findPage(page, scheduleJob);
    }

    @Override
    @Transactional(readOnly = false)
    public int save(ScheduleJob scheduleJob) {
        if (StringUtils.isEmpty(scheduleJob.getId())) {
            scheduleJob.setStatus(ScheduleStatus.PAUSE.getValue());
            scheduleJob.setId(UUID.randomUUID().toString().replace("-",""));
            ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
        } else{
            ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
        }
        return super.save(scheduleJob);
    }

    @Transactional(readOnly = false)
    public void delete(ScheduleJob scheduleJob) {
        super.remove(scheduleJob);
        ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getId());
    }

    @Transactional(readOnly = false)
    public void pause(String jobId) {
        ScheduleJob scheduleJob = dao.get(jobId);
        if(scheduleJob != null){
            ScheduleUtils.pauseJob(scheduler, jobId);
            scheduleJob.setStatus(ScheduleStatus.PAUSE.getValue());
            dao.update(scheduleJob);
        }
    }

    @Transactional(readOnly = false)
    public void resume(String jobId) {
        ScheduleJob scheduleJob = dao.get(jobId);
        if(scheduleJob != null){
            ScheduleUtils.resumeJob(scheduler, jobId);
            scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
            dao.update(scheduleJob);
        }
    }

}
