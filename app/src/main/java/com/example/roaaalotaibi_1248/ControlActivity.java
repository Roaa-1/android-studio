package com.example.roaaalotaibi_1248;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ControlActivity extends AppCompatActivity implements View.OnClickListener {
    Button insert, update, delete, view, viewAll;
    SQLiteDatabase db;
    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_activity);

        toolbar = (Toolbar)findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        insert = (Button) findViewById(R.id.insert);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        view = (Button) findViewById(R.id.view);
        viewAll = (Button) findViewById(R.id.viewAll);


        db = openOrCreateDatabase("Club_Members", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS member(id VARCHAR PRIMARY KEY ,name VARCHAR , level VARCHAR); ");


        insert.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        view.setOnClickListener(this);
        viewAll.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {


        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        if (v == insert) {

            Intent intent = new Intent(this, InsertActivity.class);
            startActivity(intent);
        }
        else if (v == update) {

            Intent intent = new Intent(this, UpdateActivity.class);
            startActivity(intent);

        }
        else if (v == delete) {

            Intent intent = new Intent(this, DeleteActivity.class);
            startActivity(intent);
        }
        else if (v == view) {

            Intent intent = new Intent(this, ViewActivity.class);
            startActivity(intent);

        }
        else if (v == viewAll) {
            db.execSQL("DELETE FROM member WHERE (id IS NULL OR id  = '') OR (name IS NULL OR name = '') OR (level IS NULL OR level = '')");
            Cursor c = db.rawQuery("SELECT * FROM member", null);

            if (c.getCount() == 0) {
                Toast toast = Toast.makeText(context, "No Members", duration);
                toast.show();
            }
            else {

                StringBuffer buffer = new StringBuffer();

                while (c.moveToNext()) {
                    buffer.append("ID: " + c.getString(0) + "\n");
                    buffer.append("Name: " + c.getString(1) + "\n");
                    buffer.append("Level: " + c.getString(2) + "\n");
                    buffer.append("_______________________________________________________\n\n");
                }

                String data = buffer.toString();
                Intent intent = new Intent(this, ViewAllActivity.class);
                intent.putExtra("Data", data);
                startActivity(intent);
            }
        }
    }

    // Action Bar
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.aboutProject:
            {
                Intent intent = new Intent(this,About_Project.class);
                startActivity(intent);
                return true;
            }
            case R.id.exit:
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to exit?").setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}