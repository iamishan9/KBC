package com.example.ib.kbc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Bhagya on 12/5/2016.
 */


public class DatabaseHelper extends SQLiteOpenHelper {

    static String dbname = "pass";
    String createTable = "CREATE TABLE if not exists `ansQsnLevel1` (\n" +
            "\t`SN`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`question`\tTEXT,\n" +
            "\t`ansA`\tTEXT,\n" +
            "\t`ansB`\tTEXT,\n" +
            "\t`ansC`\tTEXT,\n" +
            "\t`ansD`\tTEXT,\n" +
            "\t`correctAns`\tTEXT\n" +
            ")";
    String creatRandomTable = "CREATE TABLE if not exists `RandomTable` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`randomid`\tINTEGER\n" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, dbname, null, 1);
        getWritableDatabase().execSQL(createTable);
        getWritableDatabase().execSQL(creatRandomTable);
        // getWritableDatabase().execSQL(createTalble2);
    }


    public void InsertQsnAns(ContentValues cv) {
        getWritableDatabase().insert("questionset1", "", cv);
    }

    public void InsertRandomid(ContentValues cv) {
        getWritableDatabase().insert("RandomTable", "", cv);
    }

    public qsn_ans_data getdata(int id) {

        String sql = "Select * from questionset1 where id=" + id;
        Cursor c = getWritableDatabase().rawQuery(sql, null);
        qsn_ans_data data = new qsn_ans_data();
        while (c.moveToNext()) {

            data.questions = c.getString(c.getColumnIndex("questions"));
            data.ansA = c.getString(c.getColumnIndex("ansA"));
            data.ansB = c.getString(c.getColumnIndex("ansB"));
            data.ansC = c.getString(c.getColumnIndex("ansC"));
            data.ansD = c.getString(c.getColumnIndex("ansD"));
            data.correctAns = c.getString(c.getColumnIndex("correctAns"));


        }
        c.close();
        return data;

    }

    public ArrayList<Integer> getListofRandomid() {
        String sql = "Select * from RandomTable";

        Cursor c = getWritableDatabase().rawQuery(sql, null);
        ArrayList list = new ArrayList();
        while (c.moveToNext()) {

            // info.id= c.getString(c.getColumnIndex("id"));
            list.add(c.getInt(c.getColumnIndex("randomid")));


        }
        c.close();
        return list;

    }

    public void resetdata() {

String sqlforreset="UPDATE RandomTable\n" +
        "SET randomid = NULL";
        getWritableDatabase().execSQL(sqlforreset);
      //  Cursor c=getWritableDatabase().rawQuery(sqlforreset,null);

}

    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
