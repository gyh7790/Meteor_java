package junit;

import com.gyh.MeteorApplication;
import com.gyh.common.rabbitmq.MyRabbitProducer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * @author gyh
 * @Date 2021/2/25 19:05
 */
@WebAppConfiguration
@SpringBootTest(classes = MeteorApplication.class)
public class TestRabbit {


    @Resource
    private MyRabbitProducer myRabbitProducer;

    @Test
    public void sender(){
        myRabbitProducer.send();
    }

}
