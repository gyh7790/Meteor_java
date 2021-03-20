package com.gyh.common.constant;

/**
 * @author guoyh
 * @Date 2019/5/27 22:55
 */
public class Constant {

    public enum BusinessStatus
    {
        /**
         * 成功
         */
        SUCCESS,

        /**
         * 失败
         */
        FAIL,
    }

    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 这里是url中权限标识
     */
    public enum IsAuth {

        /**
         * 不需要权限校验
         */
        NOT(0),
        /**
         * 需要权限校验
         */
        AUTH(1);

        private int value;

        IsAuth(int value) {this.value = value;}

        public int getValue() {
            return value;
        }
    }

    /**
     * 系统使用的数字
     */
    public enum NumEnum {

        /**
         * 数字 0
         */
        ZERO(0),
        /**
         * 数字 1
         */
        ONE(1);


        private int value;

        NumEnum(int value) {this.value = value;}

        public int getValue() {
            return value;
        }

        public String getStrValue(){
            return String.valueOf(value);
        }

    }

    /**
     * 系统 常用符号
     */
    public enum SymEnum {
        /**
         * 逗号
         */
        COMMA(","),
        /**
         * 下划线
         */
        UNDER_LINE("_"),
        /**
         * 点
         */
        DOT(".");

        private String value;

        SymEnum(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }


    /**
     *
     */
    public enum KeyEnum {

        /**
         * 分页 页码
         */
        PAGE_NO("pageNo"),
        /**
         * 上传 类型
         */
        MULTIPART("multipart"),
        /**
         * 方法名称
         */
        FUNC_NAME("funcName"),

        /**
         * 重新 包装
         */
        REPAGE("repage"),

        /**
         * 排序 字段
         */
        ORDER_BY("orderBy");

        private String value;

        KeyEnum(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}