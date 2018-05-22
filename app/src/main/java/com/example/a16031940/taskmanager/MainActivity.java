package com.example.a16031940.taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
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

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, taskArrayList);
        lv.setAdapter(arrayAdapter);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(i, 3);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {

Notifiers(requestCode);
            }
        }
    }

    public void Notifiers(int code){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND,3);

        Intent intent = new Intent(MainActivity.this,MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,code,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pendingIntent);

    }
}
