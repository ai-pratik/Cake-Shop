package com.example.bakeryapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
   ImageView hamburger;
    EditText username, password;
    NavigationView nav;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout= (DrawerLayout)findViewById(R.id.drawerLayout);
       hamburger= (ImageView)findViewById(R.id.hamburger);
        nav = (NavigationView) findViewById(R.id.navmenu);
        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        btn_login= findViewById(R.id.btn_login);
    }

    public void singup(View view) {
        Intent intent  = new Intent(this, RegistrationScreenActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        if (isConnectingToInternet())
        {
            //get the root
            final String userID = username.getText().toString().trim();
            final String pass = password.getText().toString().trim();
            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Users");

           DatabaseReference child = myRef.child(userID);

            child.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String name = (String) dataSnapshot.child("id").getValue();
                    String password = (String) dataSnapshot.child("pass").getValue();
                    if (name.equals(userID)  && password.equals(pass)){
                        Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(LoginActivity.this, cake_animation.class);
                        intent.putExtra("UserID",userID);
                        startActivity(intent);
                    }
                    else{

                      Toast.makeText(LoginActivity.this, "Invalid ID or password", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("kk", "Failed to read value.", error.toException());
                }
            });
        }
        else{
            Intent intent  = new Intent(LoginActivity.this, No_internet.class);
            startActivity(intent);

        }


        }
    public boolean isConnectingToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }
}
