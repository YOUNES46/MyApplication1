package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class ProfilActivity extends AppCompatActivity {
 String name_student,email_student,wilaya_student;
 TextView name,email,wilaya;
TextView emaull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

          name_student =getIntent().getExtras().getString("name");
          email_student = getIntent().getExtras().getString("email");
          wilaya_student =getIntent().getExtras().getString("wilaya");

          name =(TextView) findViewById(R.id.profil_nom);
        email =(TextView) findViewById(R.id.emailstudent);
        wilaya =(TextView) findViewById(R.id.wilyastudent);

          name.setText(name_student);
          email.setText(email_student);
          wilaya.setText(wilaya_student);

    }
}
