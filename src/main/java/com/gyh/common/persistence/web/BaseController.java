package com.gyh.common.persistence.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gyh.common.persistence.page.PageModel;
import com.gyh.common.persistence.page.PageParam;
import com.gyh.common.tools.Assert;
import com.gyh.common.tools.DateUtils;
import com.gyh.common.tools.SqlUtils;
import com.gyh.common.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * 控制器支持类
 * @author guoyh
 * @version 2013-3-23
 */
public abstract class BaseController {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 参数绑定异常
     */
    @ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class, ServletException.class})
    public String bindException() {
        return "error/400";
    }

    /**
     * 设置请求分页数据
     */
    protected void setPage() {
        PageModel page = PageParam.buildPageRequest();
        Integer pageNo = page.getPageNo();
        Integer pageSize = page.getPageSize();
        if (Assert.isNotNull(pageNo) && Assert.isNotNull(pageSize)) {
            String orderBy = SqlUtils.escapeOrderBySql(page.getOrderBy());
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
    }

    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
//			@Override
//			public String getAsText() {
//				Object value = getValue();
//				return value != null ? DateUtils.formatDateTime((Date)value) : "";
//			}
        });
    }

}
