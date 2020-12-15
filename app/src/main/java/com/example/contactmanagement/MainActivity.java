package com.example.contactmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
// import android.support.v7.app.AppCompatActivity;

public class MainActivity extends Activity{//AppCompatActivity {
    public final static String EXTRA_MESSAGE = "MESSAGE";

    private ListView obj;
    DBHelper mydb;

    Button mNewContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);

        // Get all the contacts from the database
        ArrayList array_list = mydb.getAllContacts();

        // Put all the contacts in an array
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);

        // Display the contacts in the ListView object
        obj = (ListView)findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);

        // At the click on an item, start a new activity that will display the content of the database
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {

                // The index of the COntact that will be shown in the new activity DislayActivity.java
                int id_To_Search = position + 1;
                //
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);
                Intent intent = new Intent(getApplicationContext(),DisplayContact.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }

    public void onNewContactClick(View v){

        Bundle dataBundle = new Bundle();
        dataBundle.putInt("id", 0);

        Intent intent = new Intent(getApplicationContext(),DisplayContact.class);
        intent.putExtras(dataBundle);

        startActivity(intent);
    }

}

