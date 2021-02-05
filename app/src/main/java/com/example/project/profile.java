package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class profile extends AppCompatActivity {
    private Database_signup database_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ListView listView=findViewById(R.id.listview2);
        ArrayList<String> list= new ArrayList<>();

        database_signup=new Database_signup(this);
        try {
            SQLiteDatabase database = database_signup.getReadableDatabase();

            Cursor cursor=database.rawQuery("SELECT * from teacher where name='"+Main_page2.user+"' or name='"+Main_page.user+"'",null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        String name1=cursor.getString(cursor.getColumnIndex("name"));
                        String email1=cursor.getString(cursor.getColumnIndex("email"));
                        String phone_no=cursor.getString(cursor.getColumnIndex("phone_no"));
                        String subjects=cursor.getString(cursor.getColumnIndex("subjects"));
                        String qualification=cursor.getString(cursor.getColumnIndex("qualification"));
                        String work_exp=cursor.getString(cursor.getColumnIndex("work_experience"));
                        String city=cursor.getString(cursor.getColumnIndex("city_name"));
                        String address=cursor.getString(cursor.getColumnIndex("address"));
                        String profession=cursor.getString(cursor.getColumnIndex("profession"));
                        String size=cursor.getString(cursor.getColumnIndex("size"));
                        list.add("Name : "+name1+"\n\n"+"Email : "+ email1+"\n"+"Phone Number : "+phone_no+"\n"+"Subjects : "+subjects+"\n"+"Qualifications : "+qualification+"\n"+"Work Experience(In Years or Months) : "+work_exp+"\n"+"City Name : "+city+"\n"+"Address : "+address+"\n"+"Profession : "+profession+"\n"+"Maximum Size Of Batch : "+size);
                        cursor.moveToNext();
                    }
                    cursor.close();
                    database.close();
                } else {
                    cursor.close();
                    database.close();
                }
            }
            else{
                database.close();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);


    }
}