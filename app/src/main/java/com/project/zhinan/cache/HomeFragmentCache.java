package com.project.zhinan.cache;

import android.content.Context;

import com.jakewharton.disklrucache.DiskLruCache;
import com.project.zhinan.MyApplication;
import com.project.zhinan.utils.AppUtils;
import com.project.zhinan.utils.FileUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by ymh on 2016/4/13.
 */
public class HomeFragmentCache {
    Context context;
    private DiskLruCache mDiskLruCache;

    public HomeFragmentCache(Context context) {
        this.context = context;
        initCache();
    }

    /**
     * 初始化缓存
     */
    public void initCache() {
        try {
            File cacheDir = FileUtil.getDiskCacheDir(context, "datas");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, AppUtils.getVersionCode(context), 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入缓存
     */
    public void setDiskLruCache(String data) {

    }

    /**
     * 读取缓存
     */
    public String readDiskLruCache() {
        return " ";
    }

    /**
     * 删除缓存
     */
    public boolean deleteDiskLruCache() {
        return false;
    }
}
