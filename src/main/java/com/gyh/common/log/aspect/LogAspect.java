package com.gyh.common.log.aspect;

import com.alibaba.fastjson.JSON;
import com.gyh.common.constant.Constant;
import com.gyh.common.log.annotation.Log;
import com.gyh.common.tools.Assert;
import com.gyh.common.tools.DateUtils;
import com.gyh.common.tools.JsonUtils;
import com.gyh.common.tools.StringUtils;
import com.gyh.common.utils.ServletUtils;
import com.gyh.system.sys.entity.OperLog;
import com.gyh.system.sys.entity.User;
import com.gyh.system.sys.service.OperLogService;
import com.gyh.system.sys.utils.IpUtils;
import com.gyh.system.sys.utils.UserUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 操作日志记录处理
 * @author gyh
 * @Date 2021/3/19 23:51
 */
@Aspect
@Component()
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private OperLogService operLogService;

    /**
     *  这里是使用注解的方式定位需要拦截的方法
     * 也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
     * 切点表达式:   execution(...) 如果以注解可以使用 && 链接
     * @Pointcut("execution(* *.getReceiptInfo(..))")
     */
    @Pointcut("@annotation(com.gyh.common.log.annotation.Log)")
    public void logPointCut(){}

    /**
     * 处理完请求后执行
     * rsult为返回内容
     */
    @AfterReturning(value="logPointCut()",returning="result")
    public void afterReturningMethod(JoinPoint joinPoint,Object result){
        // 返回处理逻辑...
        handleLog(joinPoint, null, result);
    }

    /**
     * 异常通知
     * e异常内容
     */
    @AfterThrowing(value="logPointCut()",throwing="e")
    public void afterReturningMethod(JoinPoint joinPoint, Exception e){
        // 异常 处理逻辑
        handleLog(joinPoint, e, null);
    }

    /**
     * 处理 日志
     */
    private void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult){
        try {

            // 获得注解
            Log log = getAnnotationLog(joinPoint);
            if (log == null) {
                return;
            }

            OperLog operLog = new OperLog();

            operLog.setStatus(Constant.BusinessStatus.SUCCESS.ordinal());

            User user = UserUtils.get();
            if (Assert.isNotNull(user)) {
                operLog.setOperName(user.getName());
            }

            // 请求的IP地址
            operLog.setOperIp(IpUtils.getIpAddr(ServletUtils.getRequest()));

            // 请求 IP 地址
            operLog.setOperLocation(IpUtils.getAddrByIp(operLog.getOperIp()));

            // 返回参数
            operLog.setJsonResult(JsonUtils.toStrByJson(jsonResult));

            // 请求地址
            operLog.setOperUrl(ServletUtils.getRequest().getRequestURI());

            // 类型 名称
            String className = joinPoint.getTarget().getClass().getName();
            // 方法名称
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");

            // 设置action动作
            operLog.setBusinessType(log.businessType().ordinal());
            // 设置标题
            operLog.setTitle(log.title());
            // 设置操作人类别
            operLog.setOperatorType(log.operatorType().ordinal());
            // 是否需要保存request，参数和值

            if (e != null)
            {
                operLog.setStatus(Constant.BusinessStatus.FAIL.ordinal());
                operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }


            // 请求方式
            String method = ServletUtils.getRequest().getMethod();
            operLog.setRequestMethod(method);
            operLog.setOperTime(DateUtils.now());

            // 参数 是否入库
            if (log.isSaveRequestData()) {
                String requestMethod = operLog.getRequestMethod();
                if (HttpMethod.POST.name().equals(requestMethod)) {
                    String param = ServletUtils.getParameter();
                    if (StringUtils.isEmpty(param)) {
                        operLog.setOperParam(ServletUtils.getBody());
                    } else {
                        Map<String,Object> map = new HashMap<>();
                        map.put("param", JsonUtils.toJsonObject(param));
                        map.put("param", JsonUtils.toJsonObject(ServletUtils.getBody()));
                        operLog.setOperParam(ServletUtils.getBody());
                    }
                } else {
                    operLog.setOperParam(ServletUtils.getParameter());
                }
            }

            operLogService.save(operLog);

        } catch (Exception exp) {
            // 记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息:{}", exp.getMessage());
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray)
    {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0)
        {
            for (int i = 0; i < paramsArray.length; i++)
            {
                if (!isFilterObject(paramsArray[i]))
                {
                    try
                    {
                        Object jsonObj = JSON.toJSON(paramsArray[i]);
                        params += jsonObj.toString() + " ";
                    }
                    catch (Exception e)
                    {
                    }
                }
            }
        }
        return params.trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o)
    {
        Class<?> clazz = o.getClass();
        if (clazz.isArray())
        {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        }
        else if (Collection.class.isAssignableFrom(clazz))
        {
            Collection collection = (Collection) o;
            for (Iterator iter = collection.iterator(); iter.hasNext();)
            {
                return iter.next() instanceof MultipartFile;
            }
        }
        else if (Map.class.isAssignableFrom(clazz))
        {
            Map map = (Map) o;
            for (Iterator iter = map.entrySet().iterator(); iter.hasNext();)
            {
                Map.Entry entry = (Map.Entry) iter.next();
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse;
    }
}
