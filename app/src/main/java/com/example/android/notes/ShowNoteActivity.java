package com.example.android.notes;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);
        TextView tvTitle = (TextView) findViewById(R.id.note_title);
        TextView tvText = (TextView) findViewById(R.id.note_text);
        int id = this.getIntent().getExtras().getInt("id");
        DBHelper dbHelper = new DBHelper(this);
        Cursor data = dbHelper.getData(id);

        String title = data.getString(data.getColumnIndexOrThrow("title"));
        String text = data.getString(data.getColumnIndexOrThrow("text"));

        tvTitle.setText(title);
        tvText.setText(text);
    }
}
