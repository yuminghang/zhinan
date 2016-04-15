package com.project.zhinan.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by deng on 16-4-15.
 */
public class HistorySqlliteHelper extends SQLiteOpenHelper {
    public HistorySqlliteHelper(Context context) {
        super(context, "HistoryDb", null, 1);
    }

    public HistorySqlliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS history_table ( id integer primary key autoincrement, ad_id text UNIQUE, rbs integer," +
                "ad_url text , if_get integer ," +
                " create_time text , update_time text )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
