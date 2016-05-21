package com.other.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Administrator on 2016/5/12.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "studySample.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME_INFO = "expenditure";
    private static final String SQL_CREATE_TABLE_INFO = "CREATE　TABLE "+TABLE_NAME_INFO+"("
            + BaseColumns._ID+"INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"table.name" +"VARCHAR(50) DEFAULT 'expenditure',"
            + "time" + " VARCHAR(10),"//时间YYYYMMDD格式
            + "food" + " VARCHAR(10),"//饮食
            + "shopping" + " VARCHAR(10),"//购物
            + "play" + " VARCHAR(10),"//娱乐
            + "medicine" + " VARCHAR(10),"//医疗
            + "other" + " VARCHAR(10)"
            + ");";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
