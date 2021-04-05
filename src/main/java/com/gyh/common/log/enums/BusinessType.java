package com.gyh.common.log.enums;

/**
 * 业务 的操作类型
 * @author gyh
 * @Date 2021/3/19 23:47
 */
public enum BusinessType {
    /**
     * 其它
     */
    OTHER,
    /**
     * 查询
     */
    SELECT,
    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 授权
     */
    GRANT,

    /**
     * 导出
     */
    EXPORT,

    /**
     * 导入
     */
    IMPORT,

    /**
     * 强退
     */
    FORCE,

    /**
     * 生成代码
     */
    GENCODE,

    /**
     * 清空数据
     */
    CLEAN,
}
