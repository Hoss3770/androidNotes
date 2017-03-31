package com.example.android.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String NOTES_TABLE_NAME = "notes";
    public static final String NOTES_COLUMN_ID = "id";
    public static final String NOTES_COLUMN_TITLE = "title";
    public static final String NOTES_COLUMN_TEXT = "text";
    private HashMap hp;

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table notes (id INTEGER PRIMARY KEY, title text,text text)");

    }

    @Override
    public void  onUpgrade(SQLiteDatabase db , int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
    }

    public boolean insertNote (String title, String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues note = new ContentValues();
        note.put("title", title);
        note.put("text",text);
        db.insert("notes", null, note);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from notes where id = ? ", new String []  { Integer.toString(id)});
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db,NOTES_TABLE_NAME);
        return numRows;
    }

    public boolean updateNote(Integer id,String title,String text){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues note = new ContentValues();
        note.put("title",title);
        note.put("text",text);
        db.update("notes",note,"id = ?", new String [] {Integer.toString(id)});
        return true;
    }

    public boolean deleteNote(Integer id){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("notes","id = ?",new String []{Integer.toString(id)});
        return  true;
    }





}
