package com.example.a16031940.taskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<String> taskArrayList;
    ArrayAdapter arrayAdapter;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper mydb = new DBHelper(this);
        lv = findViewById(R.id.lv);
        taskArrayList = new ArrayList<String>();
        ArrayList mydbArray = mydb.getAllTasks();
        taskArrayList.addAll(mydbArray);

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,taskArrayList);
        lv.setAdapter(arrayAdapter);



    }
}
