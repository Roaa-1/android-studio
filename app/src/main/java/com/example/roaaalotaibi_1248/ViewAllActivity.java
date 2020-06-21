package com.example.roaaalotaibi_1248;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ViewAllActivity extends AppCompatActivity {
    TextView textView;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_activity);
        textView = findViewById(R.id.viewPage);


        Intent intent = getIntent();
        String data = intent.getExtras().getString("Data");
        textView.setText(data);
    }

}
