package com.test.smartschool.Utils;


//Log的简单封装 发布时可以将isopen修改为true即可
public final class Log {
    private static final boolean isopen = true;

    private static final String TAG = "TAGTEXT";



    public static void v(String message){
        if (isopen){
            android.util.Log.v(TAG,message);
        }
    }

    public static void d(String message){
        if (isopen){
            android.util.Log.d(TAG,message);
        }
    }


    public static void i(String message){
        if (isopen){
            android.util.Log.i(TAG,message);
        }
    }

    public static void w(String message){
        if (isopen){
            android.util.Log.w(TAG,message);
        }
    }

    public static void e(String message){
        if (isopen){
            android.util.Log.e(TAG,message);
        }
    }
}
