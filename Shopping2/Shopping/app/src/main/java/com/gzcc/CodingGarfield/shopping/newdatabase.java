package com.gzcc.CodingGarfield.shopping;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rongjielong on 2016/5/3.
 */
public class newdatabase extends SQLiteOpenHelper {
    public newdatabase(Context context){
        super (context,"newdatabase",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String str1="create table user(_id integer primary key autoincrement,username text,password text,phone text,email text)";
        db.execSQL(str1);
        String str2 = "create table business(_id integer primary key autoincrement,username text,password text,phone text,email text,tradename text)";
        db.execSQL(str2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }
}
