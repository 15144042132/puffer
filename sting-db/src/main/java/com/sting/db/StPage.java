//package com.sting.db;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import lombok.Data;
//
//import java.util.Map;
//
///**
// * 分页工具
// *
// * @author 王永吉
// */
//@Data
//public class StPage<T> extends Page<T> {
//    //是否为首页
//    boolean isFirst;
//    //是否为最后一页
//    boolean isLast;
//
//    /**
//     * current 页数（默认1）
//     * pageSize 分页大小（默认10）
//     */
//    public StPage(Map param) {
//        if (param == null) {
//            setCurrent(1L);
//            setSize(10L);
//            return;
//        }
//        Object current = param.get("current");
//        Object pageSize = param.get("size");
//        if (isBlank(current) || Integer.parseInt(current.toString()) <= 0) current = 1L;
//        if (isBlank(pageSize) || Integer.parseInt(current.toString()) <= 0) pageSize = 10L;
//
//        setCurrent(Long.parseLong(current + ""));
//        setSize(Long.parseLong(pageSize + ""));
//    }
//
//    /**
//     * current 页数
//     * pageSize 分页大小
//     */
//    public StPage(Object current, Object pageSize) {
//        if (isBlank(current) || Integer.parseInt(current.toString()) <= 0) current = 1L;
//        if (isBlank(pageSize) || Integer.parseInt(current.toString()) <= 0) pageSize = 10L;
//
//        setCurrent(Long.parseLong(current + ""));
//        setSize(Long.parseLong(pageSize + ""));
//    }
//
//    private static boolean isBlank(Object obj) {
//        if (obj == null) return true;
//
//        if (obj instanceof String) {
//            return obj.toString().replace(" ", "").trim().equals("");
//        }
//        return false;
//    }
//
//}
