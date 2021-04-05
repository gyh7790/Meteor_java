package com.gyh.common.utils;

import com.gyh.common.exception.RRException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 * @author gyh
 * @Date 2020/6/9 21:46
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws RRException  校验不通过，则报RRException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws RRException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for(ConstraintViolation<Object> constraint:  constraintViolations){
                msg.append(constraint.getPropertyPath()+":").append(constraint.getMessage()+";");
            }
            throw new RRException(msg.toString());
        }
    }

    /**
     * 校验 对象的字段
     * @param obj 被验证的对象
     * @return
     */
    public static Map<String,String> validateModel(Object obj) {//验证某一个对象
        //验证某个对象,，其实也可以只验证其中的某一个属性的
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
        Iterator<ConstraintViolation<Object>> iter = constraintViolations.iterator();

        Map<String,String> mapList =  new HashMap<>();
        while (iter.hasNext()) {
            ConstraintViolation<Object> con = iter.next();
            mapList.put(con.getPropertyPath().toString(),con.getMessage());
        }
        return mapList;
    }
}
