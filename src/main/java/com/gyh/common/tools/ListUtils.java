package com.gyh.common.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * list 工具类
 * @author guoyh
 * @Date 2019/6/25 16:21
 */
public class ListUtils {


    /**
     * 创建 新的集合
     * @param <E>
     * @return
     */
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    /**
     * 检验 集合中是否存在某个元素
     * @param list
     * @return
     */
    public static boolean contains(List<?> list, Object obj){
        if (isNotEmpty(list)) {
            return list.contains(obj);
        } else {
            return false;
        }
    }

    /**
     * 获取集合中元素的个数
     * @param list
     * @return
     */
    public static int size(List<?> list){
        if (isNotEmpty(list)) {
            return list.size();
        } else {
            return 0;
        }
    }


    /**
     * List 集合为空
     * 如果 一个元素都没有则返回 true, 否则 false
     * @param list
     * @return
     */
    public static boolean isEmpty(List<?> list){
        return !isNotEmpty(list);
    }

    /**
     * List 集合非空
     * 如果 含有元素则返回 true, 否则 false
     * @param list
     * @return
     */
    public static boolean isNotEmpty(List<?> list){
        return list != null && !list.isEmpty();
    }

    /**
     * 是否 含有交集 返回 true 含有交集,否则 false
     * @param list1
     * @param list2
     * @return
     */
    public static boolean isIntersection(List<String> list1,List<String> list2){
        List<String> collect1 = list1.stream().filter(str -> list2.contains(str)).collect(Collectors.toList());
        return isNotEmpty(collect1);
    }

    /**
     * 是否 含有交集 返回 true 含有交集,否则 false
     * @param list1
     * @param list2
     * @return
     */
    public static List<String> listIntersection(List<String> list1,List<String> list2){
        return list1.stream().filter(str -> list2.contains(str)).collect(Collectors.toList());
    }

    /**
     * 校验 list 元素 是否有重复值
     * 使用条件： T 类型 必选重写 equals 和 hashCode
     * 校验 过程中使用的是：equals 和 hashCode
     * @param list list 流
     * @param <T> 重复数据结合
     * @return
     */
    public static <T> List<T> getDuplicateElements(List<T> list) {
        return list.stream()
                .collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b)) // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
                .entrySet().stream() // Set<Entry>转换为Stream<Entry>
                .filter(entry -> entry.getValue() > 1) // 过滤出元素出现次数大于 1 的 entry
                .map(entry -> entry.getKey()) // 获得 entry 的键（重复元素）对应的 Stream
                .collect(Collectors.toList()); // 转化为 List
    }

}
