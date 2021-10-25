package com.example.lab5_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static String usernameKey = "user";

    public void clickFunction(View view) {
        EditText username = (EditText) findViewById(R.id.username);
//        String str = username.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_notes", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(usernameKey, username.getText().toString()).apply();

//        goToActivity2(str);
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

//    public void goToActivity2(String s) {
//        Intent intent = new Intent(this, MainActivity2.class);
//        intent.putExtra("username", s);
//        startActivity(intent);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntent();

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_notes", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString(usernameKey,"").equals("")) {
            Intent intentCon = new Intent(this, MainActivity2.class);
            startActivity(intentCon);
        } else {
            setContentView(R.layout.activity_main);
        }
    }
}