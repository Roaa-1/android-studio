package com.example.roaaalotaibi_1248;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="login1";
    public static final String TABLE_NAME ="users1";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="username1";
    public static final String COL_3 ="password1";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);
        addUser("Roaa","r123123");  // It is enough to run this code once then put it is as comment.
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
    db.execSQL("CREATE TABLE IF NOT EXISTS users1( ID INTEGER PRIMARY  KEY AUTOINCREMENT, username1 TEXT, password1 TEXT);");

        }
        catch (Exception e)
        {
        e.printStackTrace();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
        catch(Exception e){
            e.printStackTrace();
    }
    }

    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username1",user);
        contentValues.put("password1",password);
        long res = db.insert("users1",null,contentValues);
        db.close();
        return  res;
    }

    public boolean checkUser(String username , String password){
         String[] columns= {COL_1} ;
         SQLiteDatabase db = getReadableDatabase();
         String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
         String[] selectionArgs ={username,password};
         Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);

         int count = cursor.getCount();
         cursor.close();
         db.close();

         if(count>0)
             return true;
         else
             return false;

    }
}
