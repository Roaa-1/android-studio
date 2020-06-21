package com.example.roaaalotaibi_1248;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity  implements View.OnClickListener{
    EditText u_id , u_name , u_level;
    Button Update;
    SQLiteDatabase db;
    private Toolbar toolbar;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);

        toolbar = (Toolbar)findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        u_id = (EditText)findViewById(R.id.u_id);
        u_name = (EditText)findViewById(R.id.u_name);
        u_level= (EditText)findViewById(R.id.u_level);
        Update=(Button)findViewById(R.id.Update);

        db = openOrCreateDatabase("Club_Members", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS member(id VARCHAR PRIMARY KEY,name VARCHAR , level VARCHAR); ");

        Update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

    if(v==Update)
    {
        if (u_id.getText().toString().isEmpty())
        {
            Toast toast = Toast.makeText(context, "All fields are required", duration);
            toast.show();
        }
        else
        {
            Cursor c=db.rawQuery("SELECT * FROM member WHERE id='"+u_id.getText()+"'", null);

            if(c.moveToFirst()) {
                db.execSQL("UPDATE member SET name='" + u_name.getText() + "', level='" + u_level.getText() +"' WHERE id='"+u_id.getText()+"'");
                Toast toast = Toast.makeText(context, " Record with ID : "+ u_id.getText()+" is updated", duration);
                toast.show();
            }
            else
            {
                Toast toast = Toast.makeText(context, "Invalid ID", duration);
                toast.show();
            }

            u_id.setText("");
            u_name.setText("");
            u_level.setText("");
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
