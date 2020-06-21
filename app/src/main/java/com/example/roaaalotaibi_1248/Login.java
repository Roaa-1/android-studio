package com.example.roaaalotaibi_1248;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Login extends AppCompatActivity implements OnClickListener {
    EditText username ,password;
    ImageButton login;
    DBHelper db ;
    private NotificationManagerCompat notificationManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username= (EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        login=(ImageButton)findViewById(R.id.imageButton2);

        db= new DBHelper(this);

        login.setOnClickListener(this);



    }

     @Override
    public void onClick(View v) {

         if(v== login)
         {
         String user = username.getText().toString();
         String pass=password.getText().toString();
         Boolean flag = db.checkUser(user,pass);

         if(flag==true)
         {

             // Notification

             notificationManager=NotificationManagerCompat.from(this);
             Notification notification= new NotificationCompat.Builder(this,App.Chanel_1_ID)
                     .setSmallIcon(R.drawable.code)
                     .setContentTitle("Welcome Back!")
                     .setContentText("Leader: " +username.getText().toString() +", your application  is ready!")
                     .setPriority(NotificationCompat.PRIORITY_HIGH)
                     .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                     .build();
             notificationManager.notify(1,notification);

             // Move to Main interface

             Intent intent = new Intent(this,ControlActivity.class);
             startActivity(intent);
         }
         else
         {
             Context context = getApplicationContext();
             int duration = Toast.LENGTH_LONG;
             Toast toast = Toast.makeText(context, "Invalid Username / Password", duration);
             toast.show();
         }
        }
    }
}
