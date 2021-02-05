package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database_signup extends SQLiteOpenHelper {

    private static final String DB_name = "project";
   private static final String Table_name = "signup";
    private static final String Table_name1 = "student";
    private static final int DB_version = 11;
    private static  final String Table_name2="teacher";

    public Database_signup(@Nullable Context context) {
        super(context, DB_name, null, DB_version);
        Log.d("table",Table_name1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_statement1="create table student(id integer primary key autoincrement,name text,email text,password text,phone_no text,category text,school_name text,percentage text,city_name text,Class text,Address text);";
        String sql_statement="create table signup(id integer primary key autoincrement,name text,email text,password text,phone_no text,category text);";
        String sql_statement2="create table teacher(id integer primary key autoincrement,name text,email text,password text,phone_no text,category text, subjects text,qualification text,work_experience text,city_name text,address text,profession text,size text);";
        db.execSQL(sql_statement);
        db.execSQL(sql_statement1);
        db.execSQL(sql_statement2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("drop table if exists "+ Table_name);
        db.execSQL("drop table if exists "+ Table_name1);
        db.execSQL("drop table if exists "+ Table_name2);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    public void addData1(String name2, String email2, String pass2, String phone_no2,String category) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       ContentValues values1 = new ContentValues();
        values1.put("name" ,name2);
        values1.put("email" ,email2);
        values1.put("password" ,pass2);
        values1.put("phone_no" ,phone_no2);
        values1.put("category",category);
        sqLiteDatabase.insert("signup",null,values1);
    }

 public void addData2(String name2, String email2, String pass2, String phone_no2,String category,String school_name,String percentage,String city,String Class,String address)
 {
     SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
     ContentValues values = new ContentValues();
     values.put("name" ,name2);
     values.put("email" ,email2);
     values.put("password" ,pass2);
     values.put("phone_no" ,phone_no2);
     values.put("category",category);
     values.put("school_name" ,school_name);
     values.put("percentage" ,percentage);
     values.put("city_name" ,city);
     values.put("Class" ,Class);
     values.put("Address",address);
     sqLiteDatabase.insert("student",null,values);

 }

    public void addData3(String name2, String email2, String pass2, String phone_no2,String category,String subject1, String qualification1, String workExperience, String city1, String address1, String profession1, String size1) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name" ,name2);
        values.put("email" ,email2);
        values.put("password" ,pass2);
        values.put("phone_no" ,phone_no2);
        values.put("category",category);
        values.put("subjects" ,subject1);
        values.put("qualification" ,qualification1);
        values.put("work_experience" ,workExperience);
        values.put("city_name" ,city1);
        values.put("address" ,address1);
        values.put("profession" ,profession1);
        values.put("size" ,size1);

        sqLiteDatabase.insert("teacher",null,values);

    }
}
