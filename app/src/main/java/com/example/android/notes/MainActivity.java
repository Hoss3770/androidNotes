package com.example.android.notes;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;



import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView myListView;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = (ListView) findViewById(R.id.notes_list_view);
        Note a =  new Note("Hello", "fdklfjdkfajdfjdfkjkfjakdfjkdjfkdjfkdfjksadjfasjfkjdfkdjfklajkfka");
        Note b =  new Note("Hello222", "qwertyu");
        Note c =  new Note("CCCCCCCC", "qsxcvbhjkoplkmnbvfdwerthjmnbvfgjkmnbvfrtybvgh");
        ArrayList<Note> data = new ArrayList<>();
        data.add(a);data.add(b);data.add(c);

        String [] titles = new String[3];
        titles[0] = a.title;
        titles[1] = b.title;
        titles[2] = c.title;

        DBHelper dbHelper = new DBHelper(this);

//        dbHelper.insertNote("hi","ffdfkjdfjdklfjsd");
//        dbHelper.insertNote("kjdfl","fdkdjflsjf");
        SQLiteDatabase db =  dbHelper.getReadableDatabase();
        Cursor all = db.rawQuery("SELECT * , id as _id FROM notes",null);

        all.moveToFirst();

        NotesAdapter adapter = new NotesAdapter(this, all);
        myListView.setAdapter(adapter);
        final Context context  = this;
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent , View view , int position , long id) {

                Intent showNote  = new Intent(context, ShowNoteActivity.class);
                showNote.putExtra("id",position + 1);
                startActivity(showNote);

            }
        });

    }
}
