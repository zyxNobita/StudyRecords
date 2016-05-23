package com.other.db.cp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/5/12.
 */
public class InfoContentProvider extends ContentProvider {
    private static final String AUTHORITY = "com.zhangqiang.other.db.InfocontentProvider";
    private static final int MATCH_CODE = 1;
    public static final Uri CONTENT_URI_INFO = Uri.parse("content://"
            + AUTHORITY + "info");
    private static final UriMatcher mUriMatcher;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY, DBHelper.DB_NAME, MATCH_CODE);
    }

    private DBHelper mDBHelper;

    @Override
    public boolean onCreate() {
        mDBHelper = new DBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder mBuilder = new SQLiteQueryBuilder();
        switch (mUriMatcher.match(uri)) {
            case MATCH_CODE:
                mBuilder.setTables(DBHelper.TABLE_NAME_INFO);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = mBuilder.query(db, projection, selection,
                selectionArgs, null, null, null);
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        switch (mUriMatcher.match(uri)) {
            case MATCH_CODE:
                long rowID = db.insert(DBHelper.TABLE_NAME_INFO, null,
                        contentValues);
                if (rowID > 0) {
                    Uri retUri = ContentUris.withAppendedId(CONTENT_URI_INFO,
                            rowID);
                    return retUri;
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int count = 0;
        switch (mUriMatcher.match(uri)) {
            case MATCH_CODE:
                count = db.delete(DBHelper.TABLE_NAME_INFO, s, strings);
                break;
            default:
                throw new IllegalArgumentException("Unknow URI :" + uri);
        }
        this.getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s,
            String[] strings) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int count = 0;
        switch (mUriMatcher.match(uri)) {
            case MATCH_CODE:
                count = db.update(mDBHelper.TABLE_NAME_INFO, contentValues, s,
                        strings);
                break;
            default:
                throw new IllegalArgumentException("Unknow URI : " + uri);
        }
        this.getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
