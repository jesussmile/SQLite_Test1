package com.example.pannam.sqlitetest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Sqlite initial command
        //USE MODE_PRIVATE FOR SECURITY REASONS AS ITS ONLY APPLICABLE TO OUR APPLICATION.
        //null is default sth to do with cursor
        SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null);
        //create table
        sqLiteDatabase.execSQL("CREATE TABLE contacts(name TEXT, phone INTEGER, email, TEXT");
        //use single quotes whereevver possible
        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('Pannam', 9849624996,'pullbackwild@gmail.com');");
        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('Burma', 225541,'burma@gmail.com');");
        //cursor is the command that returns the query
        Cursor query = sqLiteDatabase.rawQuery("SELECT *from contacts", null);
        //check if the data is there if no data will return false
        if (query.moveToFirst()) {
            //Cycle through all records
            String name = query.getString(0);
            int phone = query.getInt(1);
            String email = query.getString(2);
            Toast.makeText(getBaseContext(), "Name = " + name + " phone = " + phone + " email = " + email, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "Error retrieving data", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
