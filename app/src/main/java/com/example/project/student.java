package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class student extends AppCompatActivity {

    private TextView name_school;
    private TextView percentage;
    private TextView city;
    private TextView address;
    private Database_signup database_signup;
    public Button sign;


    public void login_click(int result)
    {
        if(result==1)
        Toast.makeText(this, "Fill the required fields", Toast.LENGTH_SHORT).show();
       }
    public void addData(String school_name,String percentage1,String city1,String class1,String address1 )
    {
          database_signup.addData2(signup.name2,signup.email2,signup.password2,signup.phone_no2,signup.text2,school_name,percentage1,city1,class1,address1);
        }
    String[] Class= {"Choose Your Class","Class I","Class II","Class III","Class IV","Class V","Class VI","Class VII","Class VIII","Class IX","Class X","Class XI","Class XII"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);

    name_school=findViewById(R.id.editTextTextPersonName);
    percentage=findViewById(R.id.editTextNumber);
    city=findViewById(R.id.editTextTextPersonName2);
    address=findViewById(R.id.editTextTextPostalAddress3);
    database_signup=new Database_signup(this);

        final Spinner spino = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(student.this, android.R.layout.simple_spinner_item,Class);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spino.setAdapter(ad);

        sign= (Button)findViewById(R.id.button4);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        if(name_school.getText().toString().matches("") || percentage.getText().toString().matches("")|| city.getText().toString().matches(""))
                {
                    login_click(1);
                }
                else
                {
                    String school_name=name_school.getText().toString();
                    String percentage1=percentage.getText().toString();
                    String city1=city.getText().toString();
                    String Class = spino.getSelectedItem().toString();
                    String address1=address.getText().toString();
                    addData(school_name,percentage1,city1,Class,address1);
                    name_school.setText("");
                    percentage.setText("");
                    city.setText("");

                    Intent intent;
                    intent = new Intent(student.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

}