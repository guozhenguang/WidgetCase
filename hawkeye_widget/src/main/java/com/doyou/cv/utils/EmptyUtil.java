package com.doyou.cv.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author guozhenguang
 * @date 2022/9/6
 * @description
 */
public class EmptyUtil {
    /**
     * 对象是否为空
     *
     * @param o String,List,Map,Object[],int[],long[]
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            if ("".equals(o.toString().trim())) {
                return true;
            }
        } else if (o instanceof List) {
            if (((List) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Map) {
            if (((Map) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Set) {
            if (((Set) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Object[]) {
            if (((Object[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof int[]) {
            if (((int[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof long[]) {
            if (((long[]) o).length == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(Object... o) {
        boolean empty = false;
        for (Object o1 : o) {
            if (isEmpty(o1)) {
                empty = true;
                break;
            }
        }
        return empty;
    }

    /**
     * 对象是否不为空
     *
     * @param o String,List,Map,Object[],int[],long[]
     * @return
     */
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    /**
     * 对象组中是否存在 Empty Object
     *
     * @param os 对象组
     * @return
     */
    public static boolean isOneEmpty(Object... os) {
        for (Object o : os) {
            return isEmpty(o);
        }
        return false;
    }

    /**
     * 对象组中是否全是 Empty Object
     *
     * @param os
     * @return
     */
    public static boolean isAllEmpty(Object... os) {
        for (Object o : os) {
            if (!isEmpty(o)) {
                return false;
            }
        }
        return true;
    }
}
