package com.example.lab5_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {

    int noteID = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView noteLines = (TextView) findViewById(R.id.noteLines);
        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID", -1);

        if (noteID != -1) {
            Note note = MainActivity2.notes.get(noteID);
            String noteContent = note.getContent();
            noteLines.setText(noteContent);
        }

    }

    public void saveMethod(View view) {
        TextView noteLines = (TextView) findViewById(R.id.noteLines);
        String content = noteLines.getText().toString();

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);

        DBHelper dbHelper = new DBHelper(sqLiteDatabase) ;

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_notes", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(MainActivity.usernameKey,"");

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteID == -1) { //Add Note
            title = "NOTE_" + (MainActivity2.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        } else {
            title = "NOTE_" + (noteID + 1);
            dbHelper.updateNote(title, date, content, username);
        }
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}