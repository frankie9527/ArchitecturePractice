package com.jyh.sixthspace.sdk.utlis;


import com.jyh.sixthspace.sdk.SixthSpaceSdk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/2.
 */

public class MyLog {
    public static String path = SixthSpaceSdk.getContext().getExternalFilesDir(null).getAbsolutePath();
    public static String name = path + File.separator + "jyhLte.txt";

    public static void write2File(String str) {
        StackTraceElement ste[] = Thread.currentThread().getStackTrace();
        StackTraceElement heihei=ste[3];
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("["+heihei.getClassName()+"]").append("["+heihei.getMethodName()+" "+ heihei.getLineNumber()+"]");
        BufferedWriter out = null;
        try {
            init();
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(name, true)));
            Date currentTime = new Date();
//            SimpleDateFormat formatter = new SimpleDateFormat(
//                    "yyyy-MM-dd HH:mm:ss");
                        SimpleDateFormat formatter = new SimpleDateFormat(
                    " HH:mm:ss");
            String dateString = formatter.format(currentTime);
//            out.write(dateString + " " +stringBuffer.toString()+" "+ str + "\r\n");
            out.write(dateString + " " + str + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    private static void init() {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static void dele() {
        File f = new File(name);
        if (f.exists()) {
            f.delete();
        }
    }
}
