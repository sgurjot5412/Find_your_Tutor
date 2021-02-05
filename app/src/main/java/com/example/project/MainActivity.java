package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static String user;
    private TextView username;
    private  TextView password1;
    public Button sign;
    public Button log;
    int result=0;
    private Database_signup database_signup;
    public void login_click(int result)
    {
        if(result==2)
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
       else if(result==3)
            Toast.makeText(this, "Wrong password or email", Toast.LENGTH_SHORT).show();
        else if(result==1)
            Toast.makeText(this, "Fill the required fields", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editTextTextEmailAddress4);
        password1 = findViewById(R.id.editTextTextPassword2);

        sign = (Button)findViewById(R.id.button2);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,signup.class);
                startActivity(intent);
            }
        });

        log= (Button)findViewById(R.id.button);
       database_signup=new Database_signup(this);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (username.getText().toString().matches("") || password1.getText().toString().matches("")) {
                    login_click(1);
                } else {
                    try {
                        SQLiteDatabase database = database_signup.getReadableDatabase();
                        Cursor cursor = database.query("signup", null, null, null, null, null, null);
                        if (null != cursor) {
                            if (cursor.moveToFirst()) {
                                for (int i = 0; i < cursor.getCount(); i++) {
                                    String email = cursor.getString(cursor.getColumnIndex("email"));
                                    String password = cursor.getString(cursor.getColumnIndex("password"));
                                    if (email.equalsIgnoreCase(username.getText().toString()) && password.equals(password1.getText().toString())) {
                                        login_click(2);
                                        user=email;
                                        result = 2;
                                        String category=cursor.getString(cursor.getColumnIndex("category"));
                                        Intent intent;
                                        if(category.equalsIgnoreCase("Student")) {
                                            intent = new Intent(MainActivity.this, Main_page.class);
                                        }
                                        else
                                            intent = new Intent(MainActivity.this, Main_page2.class);
                                        startActivity(intent);
                                        username.setText("");
                                        password1.setText("");
                                        break;
                                    }
                                    cursor.moveToNext();
                                }
                                if (result == 0)
                                    login_click(3);
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
                }
            }
        });
        }
    }
