package com.example.rishimadhok.cdf.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.rishimadhok.cdf.Beans.NotificationBean;

import java.util.ArrayList;


/**
 * Created by shopclues on 21/6/16.
 */
public class NotificationsDatabaseAdapter {

    MyDatabaseHelper MyDatabaseHelper;

    public NotificationsDatabaseAdapter(Context context)
    {
        MyDatabaseHelper = new MyDatabaseHelper(context);
    }

    public long insert(String title, String body, String date)
    {
        SQLiteDatabase db = MyDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDatabaseHelper.TITLE, title);
        contentValues.put(MyDatabaseHelper.BODY, body);
        contentValues.put(MyDatabaseHelper.DATE,date);
        long id = db.insert(MyDatabaseHelper.TABLE_NAME,null,contentValues);
        return id;
    }

    public ArrayList<NotificationBean> viewDetails()
    {
        SQLiteDatabase db = MyDatabaseHelper.getReadableDatabase();//WritableDatabase();
        ArrayList<NotificationBean> data = new ArrayList<>();
        String[] columns = {MyDatabaseHelper.UID,MyDatabaseHelper.TITLE,MyDatabaseHelper.BODY,MyDatabaseHelper.DATE};
        Cursor cursor = db.query(MyDatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
//        StringBuffer buffer = new StringBuffer();
//        int ctr = 0;
        while (cursor.moveToNext()){
                NotificationBean currentNotificationBean = new NotificationBean();
                int index1 = cursor.getColumnIndexOrThrow(MyDatabaseHelper.UID);
                int id = cursor.getInt(index1);
                int index2 = cursor.getColumnIndexOrThrow(MyDatabaseHelper.TITLE);
                String title = cursor.getString(index2);
                int index3 = cursor.getColumnIndexOrThrow(MyDatabaseHelper.BODY);
                String body = cursor.getString(index3);
                int index4 = cursor.getColumnIndexOrThrow(MyDatabaseHelper.DATE);
                String date = cursor.getString(index4);

                currentNotificationBean.title = title;
                currentNotificationBean.body = body;
                currentNotificationBean.date = date;
                currentNotificationBean.id = id;

                data.add(currentNotificationBean);

//                Log.d("Notification", "title of " + ctr + " notification is " + currentNotificationBean.title);
//                Log.d("Notification", "body of " + ctr + " notification is " + currentNotificationBean.body);
//                ctr++;
//                    buffer.append(cid +" "+ title +" "+ body+"\n");
            }
        cursor.close();
//        Log.d("data array",data.toString());

        return data;

    }

//    public String getDetail(String name)
//    {
//        SQLiteDatabase db = MyDatabaseHelper.getWritableDatabase();
//        String[] columns = {MyDatabaseHelper.USERNAME,MyDatabaseHelper.PASSWORD};
//        Cursor cursor = db.query(MyDatabaseHelper.TABLE_NAME, columns, MyDatabaseHelper.USERNAME + "='" + name + "'", null, null, null, null);
//        StringBuffer buffer = new StringBuffer();
//        while(cursor.moveToNext())
//        {
//            int index2 = cursor.getColumnIndex(MyDatabaseHelper.USERNAME);
//            String user = cursor.getString(index2);
//            int index3 = cursor.getColumnIndex(MyDatabaseHelper.PASSWORD);
//            String pass = cursor.getString(index3);
//
//            buffer.append(user +" "+ pass+"\n");
//        }
//
//        return buffer.toString();
//    }

    //    To get the id if username and password is supplied
//    public String getDetail(String name,String password)
//    {
//        SQLiteDatabase db = MyDatabaseHelper.getWritableDatabase();
//        String[] columns = {MyDatabaseHelper.UID};
//        String[] selectionArgs = {name,password};
//        Cursor cursor = db.query(MyDatabaseHelper.TABLE_NAME, columns, MyDatabaseHelper.USERNAME + " = ? AND " + MyDatabaseHelper.PASSWORD + " = ?", selectionArgs, null, null, null);
//        StringBuffer buffer = new StringBuffer();
//        while(cursor.moveToNext())
//        {
//            int index1 = cursor.getColumnIndex(MyDatabaseHelper.UID);
//            int cid = cursor.getInt(index1);
//
//            buffer.append(cid+"\n");
//        }
//
//        return buffer.toString();
//    }

//    public void updateDetail(String oldname, String newname)
//    {
//        SQLiteDatabase db = MyDatabaseHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(MyDatabaseHelper.USERNAME,newname);
//        String[] whereArgs = {oldname};
//        db.update(MyDatabaseHelper.TABLE_NAME,cv,MyDatabaseHelper.USERNAME +" = ? ",whereArgs);
//    }
//
    public int delete(int id)
    {
        SQLiteDatabase db = MyDatabaseHelper.getWritableDatabase();
        String[] whereArgs = {String.valueOf(id)};
        int count = db.delete(MyDatabaseHelper.TABLE_NAME,MyDatabaseHelper.UID + " = ? ",whereArgs);
        return count;
    }

    static class MyDatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "notifdatabase";
        private static final String TABLE_NAME = "NOTIFTABLE";
        private static final int DATABASE_VERSION = 3;
        private static final String UID = "_id";
        private static final String TITLE = "Title";
        private static final String DATE = "Date";
        private static final String BODY = "Body";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " VARCHAR(255), " + BODY + " VARCHAR(255), " + DATE + " VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        private Context context;

        public MyDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Log.d("Database","Constructor Called");
//            Toast.makeText(context,"Constructor Called",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
                Log.d("Database","onCreate was called");
//                Toast.makeText(context,"onCreate was called",Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                Log.d("Database"," " + e);
//                Toast.makeText(context," " + e,Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
                Log.d("Database","onUpgrade was called");
//                Toast.makeText(context,"onUpgrade was called",Toast.LENGTH_SHORT).show();
            }  catch (SQLException e) {
                Log.d("Database"," " + e);
//                Toast.makeText(context," " + e,Toast.LENGTH_LONG).show();
            }

        }
    }


}
