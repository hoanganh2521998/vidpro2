package com.example.vidpro2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.MediaStore;

import androidx.annotation.Nullable;


import com.example.vidpro2.Video;

import java.util.ArrayList;
import java.util.List;

public class SqlHistory extends SQLiteOpenHelper {
    private static String NAME_DB = "History.db";
    private static String TABLE_NAME = "history";
    private static int VERSTION_TABLE = 1;
    private SQLiteDatabase sqLiteDatabase;
    private ContentValues contentValues;
    private Cursor cursor;

    public SqlHistory(@Nullable Context context) {
        super(context, NAME_DB, null, VERSTION_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTaoBang = "Create table history " +
                "("+" id Text," + "title Text," + " avatar Text," + "filemp4 Text )";
        db.execSQL(queryTaoBang);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion)
        {
            db.execSQL("drop table if exists " + NAME_DB);
            onCreate(db);
        }
    }
    public void onInsertDB(String title, String avatar,String filemp4)
    {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("avatar",avatar);
        contentValues.put("filemp4",filemp4);
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }
    public List<Video> getAllHistory()
    {
        List<Video> listVideo = new ArrayList<>();
        Video video;
        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(true,TABLE_NAME,
                null,null,null,null,
                null,null,null);
        cursor = sqLiteDatabase.rawQuery("SELECT DISTINCT * FROM history ", null);
        while (cursor.moveToNext())
        {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String avatar = cursor.getString(cursor.getColumnIndex("avatar"));
            String filemp4 = cursor.getString(cursor.getColumnIndex("filemp4"));
            video = new Video(title,avatar,filemp4);
            listVideo.add(video);
        }
        return  listVideo;
    }
}
