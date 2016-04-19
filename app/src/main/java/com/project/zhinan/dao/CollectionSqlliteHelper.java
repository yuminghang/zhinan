package com.project.zhinan.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by deng on 16-4-15.
 */
public class CollectionSqlliteHelper extends SQLiteOpenHelper {
    public CollectionSqlliteHelper(Context context) {
        super(context, "CollectionDb", null, 1);
    }

    public CollectionSqlliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS collection_table ( id integer primary key autoincrement, ad_id integer, " +
                "ad_url text UNIQUE ,isCollect integer" +
                " create_time text , update_time text )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
