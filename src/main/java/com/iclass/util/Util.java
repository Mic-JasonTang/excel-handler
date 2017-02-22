package com.iclass.util;

import com.iclass.common.Constant;

/**
 * excel-handler
 * <p>
 * Created by yang.tang on 2017/2/22 10:38.
 */
public class Util {

    /**
     * get postfix of the path
     *
     * @param path 文件路径
     * @return 返回文件后缀
     */
    public static String getPostfix(String path) {
        if (path == null || Constant.EMPTY.equals(path.trim())) {
            return Constant.EMPTY;
        }
        if (path.contains(Constant.POINT)) {
            return path.substring(path.lastIndexOf(Constant.POINT) + 1, path.length());
        }
        return Constant.EMPTY;
    }
}
