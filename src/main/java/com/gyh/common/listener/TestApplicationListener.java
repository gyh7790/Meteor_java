//package com.gyh.common.listener;
//
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//
///**
// * @author gyh
// * @Date 2020/6/14 14:47
// */
//public class TestApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        if(event.getApplicationContext().getParent() == null){
//            System.out.println("系统初始化...");
//            try {
//                Thread.currentThread().sleep(1000);
//            } catch (Exception e) {
//                System.out.println("初始化异常...");
//                e.printStackTrace();
//            }
//            System.out.println("初始化完成...");
//        }
//    }
//}