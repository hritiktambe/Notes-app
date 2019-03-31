package com.hritiktambe.RoomDatabaseTest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewNoteActivity extends AppCompatActivity {

    public static final String NOTE_ADDED = "NewNote";


    private EditText newNote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);


        initview();
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent resultIntent = new Intent();
//                if (TextUtils.isEmpty(newNote.getText())){
//                    setResult(RESULT_CANCELED,resultIntent);
//                } else {
//                    String note = newNote.getText().toString();
//                    resultIntent.putExtra(NOTE_ADDED,note);
//                    setResult(RESULT_OK,resultIntent);
//                }
//                finish();
//            }
//        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_new_note,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent resultIntent = new Intent();
        if (TextUtils.isEmpty(newNote.getText())){
            setResult(RESULT_CANCELED,resultIntent);
        } else {
            String note = newNote.getText().toString();
            resultIntent.putExtra(NOTE_ADDED,note);
            setResult(RESULT_OK,resultIntent);
        }
        finish();

        return true;
    }

    private void initview()
    {
        //btnAdd = findViewById(R.id.btnadd);
        newNote = findViewById(R.id.etNewNote);
        newNote.setPadding(7,7,7,7);

    }




}
