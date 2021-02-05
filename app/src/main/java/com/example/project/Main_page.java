package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Main_page extends AppCompatActivity  {
public static String user;
    private Database_signup database_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        ListView listView=findViewById(R.id.listview);
        ArrayList<String> list= new ArrayList<>();

        database_signup=new Database_signup(this);
        try {
            SQLiteDatabase database = database_signup.getReadableDatabase();

              Cursor cursor=database.rawQuery("SELECT * from student where email='"+MainActivity.user+"'",null);
              Cursor cursor1=database.rawQuery("SELECT * from teacher",null);
            if (null != cursor1) {
                if (cursor1.moveToFirst()&&cursor.moveToFirst()) {
                    for (int i = 0; i < cursor1.getCount(); i++) {
                        String city_name1 = cursor.getString(cursor.getColumnIndex("city_name"));
                        String city_name2 = cursor1.getString(cursor1.getColumnIndex("city_name"));
                        String name1=cursor1.getString(cursor1.getColumnIndex("name"));
                        if(city_name1.equalsIgnoreCase(city_name2))
                         list.add(name1);
                        cursor1.moveToNext();
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //user=listView.getSelectedItem().toString();
                user=list.get(position);
                Intent intent=new Intent(Main_page.this,profile.class);
                startActivity(intent);
            }
        });
    }
}