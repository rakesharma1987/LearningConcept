package com.example.mydemopersonal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mydemopersonal.utility.Util;

public class DBAdapter {
    /*TODO : DATABASE NAME*/
    public static final String DBNAME = "_personal_demo_";

    /* TODO : DATABASE VERSION */
    public static final int DBVERSION = 1;

    /*TODO : Table name*/
    public static final String TABLENAME = "demo";

    /* TODO : Table column name*/;
    public static final String ROWID = "rowid";
    public static final String FNAME = "fname";
    public static final String LNAME = "lname";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String GENDER = "gender";
    public static final String PHOTO = "photo";

    private MyDBHelper myDBHelper;
    private SQLiteDatabase sqLiteDatabase;


    private String SQL = "CREATE TABLE "+ TABLENAME +"("+ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+FNAME+" text, "+LNAME+" text, "+EMAIL+" text, "+PHONE+" text, "+GENDER+" text, "+PHOTO+" TEXT)";

    public DBAdapter(@Nullable Context context) {
        myDBHelper = new MyDBHelper(context);
    }

    public DBAdapter openDatabase(){
        sqLiteDatabase = myDBHelper.getWritableDatabase();
        return this;
    }

    public void closeDatabase(){
        sqLiteDatabase.close();
    }

    // TODO : method to insert data into DB
    public void insertData(Context context, String fName, String lName, String email, String phone, String gender, String imageInbase64){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FNAME, fName);
        contentValues.put(LNAME, lName);
        contentValues.put(EMAIL, email);
        contentValues.put(PHONE, phone);
        contentValues.put(GENDER, gender);
        contentValues.put(PHOTO, imageInbase64);
        long id = sqLiteDatabase.insert(TABLENAME, null, contentValues);
        if (id == -1){
            Util.showCustomToast(context, "Insertion falid.");
        }else {
            Util.showCustomToast(context, "Data inserted successfully");
        }

    }

    // TODO : method to retrieve data from DB
    public Cursor getAllData(){
        String[] columns = {ROWID, FNAME, LNAME, EMAIL, PHONE, GENDER, PHOTO};
        return sqLiteDatabase.query(TABLENAME, columns, null, null, null, null, null);
    }

    // TODO : method to update data

    public void upDataRecord(Context context, String rowId, String fName, String lName, String email, String phone, String gender){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FNAME, fName);
        contentValues.put(LNAME, lName);
        contentValues.put(EMAIL, email);
        contentValues.put(PHONE, phone);
        contentValues.put(GENDER, gender);
        int effectedRow = sqLiteDatabase.update(TABLENAME, contentValues, rowId+"="+ROWID, null);
        Util.showCustomToast(context, ""+effectedRow +" Data updated successfully");

    }

    class MyDBHelper extends SQLiteOpenHelper{
        public MyDBHelper(@Nullable Context context) {
            super(context, DBNAME, null, DBVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //TODO : need to implement
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLENAME);
            onCreate(sqLiteDatabase);

        }
    }
}
