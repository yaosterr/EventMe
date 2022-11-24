package com.example.eventme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Title
        getSupportActionBar().setTitle("EventMe");

        //Login Activity
        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        //Register Activity
        Button buttonRegister = findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v)
        {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
        });

        //Guest Activity
        Button buttonGuest = findViewById(R.id.button_guest);
        buttonGuest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(MainActivity.this, EventGuest.class);
                startActivity(intent);
            }
        });

    }
}