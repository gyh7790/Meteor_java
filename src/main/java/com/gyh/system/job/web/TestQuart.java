package com.gyh.system.job.web;

import com.gyh.system.job.utils.ScheduleRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author gyh
 * @Date 2020/6/14 13:22
 */
@Component
public class TestQuart {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleRunnable.class);

    public void print(String str){
        logger.info("测试 定时任务 实例 -- "+str);
    }

}
