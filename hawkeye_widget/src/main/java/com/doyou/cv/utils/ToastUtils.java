package com.doyou.cv.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * @author guozhenguang
 * @date 2022/9/6
 * @description
 */
public class ToastUtils {

    public static void showLongToast(Context context, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context context, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
