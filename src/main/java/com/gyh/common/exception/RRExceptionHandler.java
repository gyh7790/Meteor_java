package com.gyh.common.exception;

import com.gyh.common.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.naming.SizeLimitExceededException;
import java.nio.file.AccessDeniedException;

/**
 * 异常处理器
 * @author guoyh
 * @version 2020-5-23
 */
@RestControllerAdvice
public class RRExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 自定义异常
     */
    @ExceptionHandler(RRException.class)
    public R handleRrException(RRException e){
        R r = new R();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());
        return r;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException e){
        logger.error(e.getMessage(), e);
        return R.error("数据库中已存在该记录");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public R handleAuthorizationException(AccessDeniedException e){
        logger.error(e.getMessage(), e);
        return R.error("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public R handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e){
        logger.error(e.getMessage(), e);
        return R.error("上传文文件太大了");
    }

    @ExceptionHandler(SizeLimitExceededException.class)
    public R handleSizeLimitExceededException(SizeLimitExceededException e){
        logger.error(e.getMessage(), e);
        return R.error("上传文文件太大了");
    }


    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return R.error();
    }

//    @ExceptionHandler(Exception.class)
//    public RRException handleException(Exception e) {
//        logger.error(e.getMessage(), e);
//        return new RRException("dfd");
//    }
}
