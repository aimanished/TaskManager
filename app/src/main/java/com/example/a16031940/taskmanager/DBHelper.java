package com.example.a16031940.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "taskManagers.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "task_table";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TASK_NAME = "task_name";
    private static final String COLUMN_TASK_DESC = "description";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNewTableSQL = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TASK_DESC + " TEXT, " + COLUMN_TASK_NAME + " TEXT )";

        db.execSQL(createNewTableSQL);

        for (int i = 0; i < 4; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TASK_NAME, "NAME " + i);
            values.put(COLUMN_TASK_DESC, "DESC " + i);
            db.insert(TABLE_NAME, null, values);
        }
        db.close();
        Log.i("info", "dummy records inserted");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

    public void insertData(String description, String tName) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_TASK_DESC, description);
        // Store the column name as key and the date as value
        values.put(COLUMN_TASK_NAME, tName);
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_NAME, null, values);
        // Close the database connection
        db.close();
    }

    public ArrayList<String> getAllTasks() {
        ArrayList<String> tasks = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TASK_NAME, COLUMN_TASK_DESC};
//        String condition = COLUMN_TASK_DESC + " Like ?";
//        String[] args = { "%" +  keyword + "%"};
        Cursor cursor = db.query(TABLE_NAME, columns, null, null,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String taskName = cursor.getString(1);
                String taskDesc = cursor.getString(2);
                tasks.add(id + " " + taskName + " "  + taskDesc);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }
}
