package com.doyou.cv.utils;

import android.util.Log;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author zhanghongjie
 * @date 2019/6/3
 * @description 使用该自定义Log打印日志
 */
public class Common {

    private static final String TAG = "KkLog_";
    private static final int LOG_I = 0;
    private static final int LOG_V = 1;
    private static final int LOG_D = 2;
    private static final int LOG_W = 3;
    private static final int LOG_E = 4;

    public static void log_d(String tag, String msg){

            Log.d(TAG + tag, msg);
            //show(LOG_D, AppConfig.YouZi.tag() + tag, msg);

    }

    public static void log_e(String tag, String msg){

            Log.e(TAG + tag, msg);
           //show(LOG_E, AppConfig.YouZi.tag() + tag, msg);

    }

    public static void log_v(String tag, String msg){

            Log.v(TAG + tag, msg);
            //show(LOG_V, AppConfig.YouZi.tag() + tag, msg);

    }

    public static void log_w(String tag, String msg){

            Log.w(TAG + tag, msg);
            //show(LOG_W, AppConfig.YouZi.tag() + tag, msg);

    }
    public static String getStackTrace(Throwable throwable) {

        String stackTrace = "";
        try (StringWriter sw = new StringWriter();
             PrintWriter pw = new PrintWriter(sw)){
            throwable.printStackTrace(pw);
            stackTrace = sw.toString();
        } catch (IOException e) {
            log_e("KkLog", "get stack trace fail !");
        }

        return stackTrace;
    }

    private static void show(int type, String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0) {
            return;
        }
        msg = msg.trim();
        int index = 0;
        int segmentSize = 3 * 1024;
        String logContent;
        while (index < msg.length()) {
            if (msg.length() <= index + segmentSize) {
                logContent = msg.substring(index);
            } else {
                logContent = msg.substring(index, segmentSize + index);
            }
            index += segmentSize;
            switch (type) {
                case LOG_I:
                    Log.i(tag, logContent.trim());
                    break;
                case LOG_V:
                    Log.v(tag, msg);
                    break;
                case LOG_D:
                    Log.d(tag, logContent.trim());
                    break;
                case LOG_W:
                    Log.w(tag, msg);
                    break;
                case LOG_E:
                    Log.w(tag, msg);
                    break;
                default:
                    break;
            }
        }
    }
}
