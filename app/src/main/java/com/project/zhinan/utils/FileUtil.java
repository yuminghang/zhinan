package com.project.zhinan.utils;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import com.project.zhinan.MyApplication;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FileUtil {

    public static String SDPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";

    /**
     * 获取缓存地址
     * if :
     * /sdcard/Android/data/<application package>/cache
     * 通常情况下多数应用程序都会将缓存的位置选择为上面这个路径。
     * 选择在这个位置有两点好处：第一，这是存储在SD卡上的，因此
     * 即使缓存再多的数据也不会对手机的内置存储空间有任何影响，
     * 只要SD卡空间足够就行。第二，这个路径被Android系统认定为
     * 应用程序的缓存路径，当程序被卸载的时候，这里的数据也会
     * 一起被清除掉，这样就不会出现删除程序之后手机上还有很多
     * 残留数据的问题。
     * else :
     * 获取到的是 /data/data/<application package>/cache 这个路径。
     *
     * @param context
     * @param uniqueName
     * @return
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 在SD卡上创建文件
     *
     * @throws IOException
     */
    public static File creatSDFile(String saveFileName) throws IOException {
        File file = new File(SDPATH + saveFileName);
        file.createNewFile();
        return file;
    }

    /**
     * 删除文件
     *
     * @param path2
     */
    public static void DeleteFileName(String path2) {
        String path = SDPATH + path2 + "/";
        File file = new File(path);
        if (file != null)
            deleteFile(file);
    }

    /**
     * 删除文件
     *
     * @param file
     */
    private static void deleteFile(File file) {
        if (file.exists() == false) {
            return;
        } else {
            if (file.isFile()) { // 是文件
                file.delete();// 删除文件
                return;
            } else if (file.isDirectory()) { // 是目录
                File[] childFile = file.listFiles();
                if (childFile == null || childFile.length == 0) {
                    file.delete();// 删除本身
                    return;
                }
                for (File f : childFile) {
                    deleteFile(f);
                }
                file.delete();// 删除本身
            }
        }
    }

    /**
     * 在SD卡上创建目录
     *
     * @param dirName
     */
    public static File creatSDDir(String dirName) {
        File dir = new File(SDPATH + dirName);
        dir.mkdir();
        return dir;
    }

    /**
     * 判断SD卡上的文件夹是否存在
     */
    public static boolean isFileExist(String fileName) {
        File file = new File(SDPATH + fileName);
        return file.exists();
    }

    /**
     * 判断SD卡上的文件夹是否存在
     */
    public static Uri getUri(String fileName) {
        File file = new File(SDPATH + fileName);
        return Uri.fromFile(file);
    }

    /**
     * 由于得到一个InputStream对象是所有文件处理前必须的操作，所以将这个操作封装成了一个方法
     *
     * @param urlStr
     * @return
     * @throws IOException
     */
    public static InputStream getInputStream(String urlStr) throws IOException {
        InputStream is = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConn = (HttpURLConnection) url
                    .openConnection();
            is = urlConn.getInputStream();
            int fileSize = urlConn.getContentLength();// 根据响应获取文件大小
            if (fileSize <= 0)
                throw new RuntimeException("无法获知文件大小 ");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return is;
    }
}
