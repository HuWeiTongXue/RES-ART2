package com.rotai.res_art2.utils;

import android.os.Environment;

public class MyConstants {
    public static final String CHAPTER2_PATH= Environment.getExternalStorageDirectory().getPath()+"/huwei/";
    public static final String CACHE_FILE_PATH=CHAPTER2_PATH+"usercache";

    public static final int MSG_FROM_CLIENT = 0;
    public static final int MSG_FROM_SERVICE = 1;

}
