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
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity implements View.OnClickListener {

    EditText v_id ;
    TextView txt1, txt2, v_name, v_level;
    Button View;
    SQLiteDatabase db;
    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);

        toolbar = (Toolbar)findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        v_id = (EditText)findViewById(R.id.v_id);
        v_name = (TextView) findViewById(R.id.v_name);
        v_level = (TextView) findViewById(R.id.v_level);
        View=(Button)findViewById(R.id.View);

        txt1 = (TextView)findViewById(R.id.txt1);
        txt2=(TextView)findViewById(R.id.txt2);

        db = openOrCreateDatabase("Club_Members", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS member(id VARCHAR PRIMARY KEY,name VARCHAR , level VARCHAR); ");

        View.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        if(v==View)
        {
            txt1.setText("");
            txt2.setText("");
            v_name.setText("");
            v_level.setText("");

            if(v_id.getText().toString().isEmpty())
            {
                Toast toast = Toast.makeText(context, "ID field is required", duration);
                toast.show();
            }
            else {
                Cursor c =db.rawQuery("SELECT * FROM member WHERE id='"+v_id.getText()+"'", null);
                if(c.moveToFirst())
                {
                    txt1.setText("Member Name : ");
                    txt2.setText("Level : ");
                    v_name.setText(c.getString(1));
                    v_level.setText(c.getString(2));
                }
                else
                {
                    Toast toast = Toast.makeText(context, "Invalid ID", duration);
                    toast.show();
                }
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
