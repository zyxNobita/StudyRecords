package com.other.db.cp;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Administrator on 2016/5/12.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "studySample.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME_INFO = "expenditure";
    private static final String SQL_CREATE_TABLE_INFO = "CREATE　TABLE "
            + TABLE_NAME_INFO + "(" + BaseColumns._ID
            + "INTEGER PRIMARY KEY AUTOINCREMENT," + "table.name"
            + "VARCHAR(50) DEFAULT 'expenditure'," + "num" + " VARCHAR(10),"
            + "name" + " VARCHAR(10)," + "age" + " VARCHAR(10)," + ");";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public DBHelper(Context context, String name,
            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name,
            SQLiteDatabase.CursorFactory factory, int version,
            DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.e("onUpgrade", "数据库升级时触发");
        Log.e("DB_VERSION", String.valueOf(DB_VERSION));
        onCreate(sqLiteDatabase);
    }

}
