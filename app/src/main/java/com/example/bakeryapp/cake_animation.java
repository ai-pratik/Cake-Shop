package com.example.bakeryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class cake_animation extends AppCompatActivity {
    LottieAnimationView animation_cake;
    TextView Username;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_animation);

        userId = getIntent().getStringExtra("UserID");

        Username = findViewById(R.id.name);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");

        DatabaseReference child = myRef.child(userId);

        child.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String Uname = (String) dataSnapshot.child("name").getValue();
                Username.setText("Hi! "+ Uname);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("kk", "Failed to read value.", error.toException());
            }
        });



        animation_cake = findViewById(R.id.animation_cake);

        animation_cake.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                Intent intent  = new Intent(cake_animation.this, HomeScreenActivity.class);
                                intent.putExtra("UserID",userId);
                                startActivity(intent);
                            }
                        },
                        1000);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }
}
