package com.gyh.executor;

import com.gyh.MeteorApplication;
import com.gyh.common.rabbitmq.MyRabbitProducer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author gyh
 * @Date 2021/4/14 10:22
 */
@WebAppConfiguration
@SpringBootTest(classes = MeteorApplication.class)
public class TestExecutor {

    @Resource
    private ExecutorService buildThreadPool;

    @Test
    public void create(){
        buildThreadPool.submit(new TackExecutor());
    }

    @Test
    public void getExecutor(){
        List<Runnable> runs = buildThreadPool.shutdownNow();
        for (Runnable run : runs) {
            System.out.println(run.toString());
        }
    }

}
