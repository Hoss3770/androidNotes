package com.example.android.notes;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CursorAdapter;
import android.widget.TextView;


public class NotesAdapter extends CursorAdapter {


    public NotesAdapter(Context context, Cursor cursor) {
        super(context,cursor,0);
    }

    @Override
    public View newView(Context context,Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.list_item_note,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        TextView tvTitle = (TextView) view.findViewById(R.id.note_list_title);
        TextView tvSubtitle = (TextView) view.findViewById(R.id.note_list_subtitle);

        String titleText = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String subtitleText = cursor.getString(cursor.getColumnIndexOrThrow("text"));
        int i = 4;
        while(subtitleText.length() <= i ){i--;}
        subtitleText = subtitleText.substring(0,i) + "...";

        tvTitle.setText(titleText);
        tvSubtitle.setText(subtitleText);
    }




}
