package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    public Button sign;
    private TextView name;
    private TextView email;
    private TextView password;
    private TextView phone_no;
    public static String name2;
    public static String email2;
    public static String password2;
    public static String phone_no2;
    public static String text2;
private Database_signup database_signup;
    public void login_click(int result)
    {
        if(result==1)
            Toast.makeText(this, "Fill the required fields", Toast.LENGTH_SHORT).show();
        if(result==2)
        Toast.makeText(this, name2+" "+email2+" "+password2, Toast.LENGTH_SHORT).show();
    }
   private void check() {
        Log.i("info",name2);
    }

    private void setName1(String name1) {
        name2=name1;
    }
  public void addData(String name2,String email2,String pass2,String phone_no2,String category )
  {
           database_signup.addData1(name2,email2,pass2,phone_no2,category);
  }
    String []names={"Choose Category","Student","Teacher"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        name=findViewById(R.id.editTextTextPersonName3);
        email=findViewById(R.id.editTextTextEmailAddress2);
        password=findViewById(R.id.editTextTextPassword3);
        phone_no=findViewById(R.id.editTextPhone2);
       database_signup= new Database_signup(this);

        final Spinner spino = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> ad = new ArrayAdapter<>(signup.this, android.R.layout.simple_spinner_item, names);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spino.setAdapter(ad);
        sign = (Button) findViewById(R.id.button3);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(email.getText().toString().matches("") || password.getText().toString().matches("")|| name.getText().toString().matches("")|| phone_no.getText().toString().matches(""))
                {
                    login_click(1);
                }
               else {
                 String name1=name.getText().toString();
                 String email1=email.getText().toString();
                 String password1=password.getText().toString();
                 String phone_no1=phone_no.getText().toString();
                 String text = spino.getSelectedItem().toString();

                 addData(name1,email1,password1,phone_no1,text);
                 setName1(name1);
                 setEmail(email1);
                 setPassword(password1);
                 setPhone(phone_no1);
                 set_Text(text);

                 login_click(2);
                    name.setText("");
                    email.setText("");
                    password.setText("");
                    phone_no.setText("");

                    Intent intent;
                    Intent intent1;
                    if (text.equals("Student")) {
                        intent = new Intent(signup.this, student.class);
                        startActivity(intent);
                    } else if (text.equals("Teacher")) {
                        intent1 = new Intent(signup.this, Teacher.class);
                        startActivity(intent1);
                    }
               }
            }
        });
    }

    private void set_Text(String text) {
        text2=text;
    }

    private void setPhone(String phone_no1) {
        phone_no2=phone_no1;
    }

    private void setPassword(String password1) {
        password2=password1;
    }

    private void setEmail(String email1) {
        email2=email1;
    }


}