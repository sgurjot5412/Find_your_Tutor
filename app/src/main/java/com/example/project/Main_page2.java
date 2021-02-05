package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Main_page2 extends AppCompatActivity {

    private Database_signup database_signup;
    public static String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page2);
        ListView listView=findViewById(R.id.listview1);
        ArrayList<String> list= new ArrayList<>();

        database_signup=new Database_signup(this);
        try {
            SQLiteDatabase database = database_signup.getReadableDatabase();

            Cursor cursor=database.rawQuery("SELECT * from teacher where email!='"+MainActivity.user+"'",null);
            Cursor cursor1=database.rawQuery("SELECT * from teacher where email='"+MainActivity.user+"'",null);
            if (null != cursor) {
                if (cursor.moveToFirst()&&cursor1.moveToFirst()) {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        String city=cursor.getString(cursor.getColumnIndex("city_name"));
                        String city1=cursor1.getString(cursor.getColumnIndex("city_name"));
                        String name1=cursor.getString(cursor.getColumnIndex("name"));
                        if(city.equalsIgnoreCase(city1))
                            list.add(name1);
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //user=listView.getSelectedItem().toString();
                user=list.get(position);
                Intent intent=new Intent(Main_page2.this,profile.class);
                startActivity(intent);
            }
        });

    }
}