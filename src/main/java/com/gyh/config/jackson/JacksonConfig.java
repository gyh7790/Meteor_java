//package com.gyh.config.jackson;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.text.SimpleDateFormat;
//
///**
// * @author gyh
// * @Date 2020/6/14 15:08
// */
//@Configuration
//public class JacksonConfig {
//
//    @Bean
//    public ObjectMapper getObjectMapper(){
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-ddHH:mm:ss"));
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
//        return objectMapper;
//    }
//}
