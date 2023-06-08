package com.example.recyclerexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ContactModel> arrayList=new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    Toolbar toolbar;
    ContactAdapter contactAdapter =new ContactAdapter(this,arrayList);
    private static final String CHANNEL_ID="Notification";
    private static final int NOTIFICATION_ID=100;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setTitle("Contacts List");
        }
        //toolbar.setTitle("Contacts List");

        recyclerView = findViewById(R.id.rvContact);
        floatingActionButton=findViewById(R.id.fbAdd);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_dialog);
                //Toast.makeText(getApplicationContext(), "Button Clicked", Toast.LENGTH_SHORT).show();
                EditText etName=dialog.findViewById(R.id.etName);
                EditText etNumber=dialog.findViewById(R.id.etNumber);
                Button btnAdd =dialog.findViewById(R.id.btnAdd);
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name=etName.getText().toString();
                        String number=etNumber.getText().toString();
                        if(name.isEmpty() || number.isEmpty())
                        {
                            Toast.makeText(getApplicationContext(), "Please fill all the details!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), name+" "+number, Toast.LENGTH_SHORT).show();
                            arrayList.add(new ContactModel(name,number));
                            contactAdapter.notifyItemInserted(arrayList.size()-1);
                            recyclerView.scrollToPosition(arrayList.size()-1);
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9292929292",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));
        arrayList.add(new ContactModel(R.drawable.person1,"A","9999999999",R.drawable.ic_baseline_call_24));

        recyclerView.setAdapter(contactAdapter);

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;
        Drawable drawable= ResourcesCompat.getDrawable(getResources(),R.drawable.imessage,null);
        BitmapDrawable bitmapDrawable=(BitmapDrawable) drawable;
        Bitmap largeIcon=bitmapDrawable.getBitmap();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification=new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.imessage)
                    .setContentText("Welcome")
                    .setSubText("Message from ABC")
                    .setChannelId(CHANNEL_ID)
                    .build();
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"New Channel",NotificationManager.IMPORTANCE_HIGH));
        }
        else
        {
            notification=new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.imessage)
                    .setContentText("Welcome")
                    .setSubText("Message from ABC")
                    .build();
        }
        notificationManager.notify(NOTIFICATION_ID,notification);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_list,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int fontStyle = Typeface.NORMAL;

        switch (item.getItemId()) {
            case R.id.bold:
                fontStyle = Typeface.BOLD;
                break;
            case R.id.italic:
                fontStyle = Typeface.ITALIC;
                break;
            case R.id.normal:
                fontStyle = Typeface.NORMAL;
                break;
        }

        item.setChecked(true);
        contactAdapter.setFontStyle(fontStyle);
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder exitDialog=new AlertDialog.Builder(MainActivity.this);
        exitDialog.setTitle("Exit?");
        exitDialog.setMessage("Do you want to exit?");
        exitDialog.setIcon(R.drawable.ic_baseline_exit_to_app_24);
        exitDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                MainActivity.super.onBackPressed();
            }
        });
        exitDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        exitDialog.show();
        //super.onBackPressed();
    }
}