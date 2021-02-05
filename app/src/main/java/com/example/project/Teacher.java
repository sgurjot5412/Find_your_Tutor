package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Teacher extends AppCompatActivity {

    private TextView subject;
    private TextView qualification;
    private TextView work_experience;
    private TextView city;
    private TextView address;
    private TextView profession;
    private TextView size;
    private Database_signup database_signup;
    public Button sign;



    public void login_click(int result)
    {
        if(result==1)
            Toast.makeText(this, "Fill the required fields", Toast.LENGTH_SHORT).show();
    }

    public void addData(String subject1,String qualification1,String workExperience,String city1,String address1,String profession1,String size1 )
    {
        database_signup.addData3(signup.name2,signup.email2,signup.password2,signup.phone_no2,signup.text2,subject1,qualification1,workExperience,city1,address1,profession1,size1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher);

        subject=findViewById(R.id.editTextTextPersonName5);
        qualification=findViewById(R.id.editTextTextPersonName4);
        work_experience=findViewById(R.id.editTextTextPersonName6);
        city=findViewById(R.id.editTextTextPostalAddress);
        address=findViewById(R.id.editTextTextPostalAddress2);
        profession=findViewById(R.id.editTextTextPersonName7);
        size=findViewById(R.id.editTextNumber2);

        database_signup=new Database_signup(this);

        sign= (Button)findViewById(R.id.button5);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subject.getText().toString().matches("") ||qualification.getText().toString().matches("")|| city.getText().toString().matches("")||work_experience.getText().toString().matches("")||address.getText().toString().matches("")||profession.getText().toString().matches("")||size.getText().toString().matches(""))
                {
                    login_click(1);
                }
                else
                {
                    String subject1= subject.getText().toString();
                    String qualification1= qualification.getText().toString();
                    String workExperience=work_experience.getText().toString();
                    String city1=city.getText().toString();
                    String address1=address.getText().toString();
                    String profession1=profession.getText().toString();
                    String size1=size.getText().toString();
                    addData(subject1,qualification1,workExperience,city1,address1,profession1,size1);
                    Intent intent;
                    intent = new Intent(Teacher.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}