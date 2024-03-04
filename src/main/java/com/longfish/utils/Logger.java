package com.longfish.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 通过io流记录日志的工具类
 */
public class Logger {
    public static void log(String info,String path){
        FileWriter fw = null;
        try {
            File file = new File(path);
            fw = new FileWriter(file,true);

            fw.write(info);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fw != null;
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void log(String info,File file){
        FileWriter fw = null;
        try {
            fw = new FileWriter(file,true);

            fw.write(info);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fw != null;
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
