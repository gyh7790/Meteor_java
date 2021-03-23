package com.gyh.common.persistence.page;

import com.gyh.common.utils.ServletUtils;

/**
 * @author gyh
 * @Date 2021/3/20 1:05
 */
public class PageParam {
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNo";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 封装分页对象
     */
    public static PageModel getPageModel()
    {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(ServletUtils.getParameterToInt(PAGE_NUM));
        pageModel.setPageSize(ServletUtils.getParameterToInt(PAGE_SIZE));
        pageModel.setOrderByColumn(ServletUtils.getParameter(ORDER_BY_COLUMN));
        pageModel.setIsAsc(ServletUtils.getParameter(IS_ASC));
        return pageModel;
    }

    public static PageModel buildPageRequest() {
        return getPageModel();
    }
}
