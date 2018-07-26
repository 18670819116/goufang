/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.ljcs.cxwl.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {
    public static File getSaveFile(Context context) {
        File file = new File(context.getFilesDir(), "pic.jpg");
        return file;
    }
    /**
     * 按行读取文本文件
     *
     * @param is
     * @return
     * @throws Exception
     */
    public static String readTextFromFile(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
