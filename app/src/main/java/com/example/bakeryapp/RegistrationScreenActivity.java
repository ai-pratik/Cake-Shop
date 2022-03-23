package com.example.bakeryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationScreenActivity extends AppCompatActivity {

    EditText edt_name, edt_email, edt_userID, edt_password;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        edt_name = findViewById(R.id.edt_name);
        edt_email = findViewById(R.id.edt_email);
        edt_userID = findViewById(R.id.edt_userID);
        edt_password = findViewById(R.id.edt_password);
    }

    public void confirm(View view) {

        myRef = FirebaseDatabase.getInstance().getReference("Users");
        DatabaseReference myref1 = myRef.child(edt_userID.getText().toString());
        myref1.child("name").setValue(edt_name.getText().toString().trim());
        myref1.child("email").setValue(edt_email.getText().toString().trim());
        myref1.child("id").setValue(edt_userID.getText().toString().trim());
        myref1.child("pass").setValue(edt_password.getText().toString().trim());

        Toast.makeText(RegistrationScreenActivity.this, "Data added Successfully", Toast.LENGTH_SHORT).show();

    }

    public void loginPage(View view) {
        Intent intent  = new Intent(RegistrationScreenActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
