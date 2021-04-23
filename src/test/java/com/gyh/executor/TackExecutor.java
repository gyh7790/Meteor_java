package com.gyh.executor;

/**
 * @author gyh
 * @Date 2021/4/14 10:23
 */
public class TackExecutor implements Runnable {

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        thread.setName("123456789");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("测试 多线程");
    }
}
