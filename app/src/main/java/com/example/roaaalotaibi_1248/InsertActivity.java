package com.example.roaaalotaibi_1248;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class InsertActivity extends AppCompatActivity implements OnClickListener{

    private Toolbar toolbar;
    EditText s_id , Name , Level;
    Button Insert, Delete, View , ViewAll , Update;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_activity);

        toolbar = (Toolbar)findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        //----
        s_id = (EditText)findViewById(R.id.s_id);
        Name = (EditText)findViewById(R.id.Name);
        Level= (EditText)findViewById(R.id.Level);
        Insert= (Button)findViewById(R.id.Insert);


        Insert.setOnClickListener(this);

        db = openOrCreateDatabase("Club_Members", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS member(id VARCHAR PRIMARY KEY,name VARCHAR , level VARCHAR); ");

    }

    @Override
    public void onClick(View v) {

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        if( v ==Insert)
        {
          if(s_id.getText().toString().isEmpty() || Name.getText().toString().isEmpty() || Level.getText().toString().isEmpty())
          {
              Toast toast = Toast.makeText(context, "All fields are required" ,duration);
              toast.show();
          }
          else {
              try{
              db.execSQL("INSERT INTO member VALUES ('" + s_id.getText() + "','" + Name.getText() + "','" + Level.getText() + "');");
              Toast toast = Toast.makeText(context, " " + Name.getText() + " is inserted successfully", duration);
              toast.show();

              s_id.setText("");
              Name.setText("");
              Level.setText("");
              }

              catch (Exception e){
                  Toast toast = Toast.makeText(context, "The ID is already in used", duration);
                  toast.show();
              }
          }

    } }


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

