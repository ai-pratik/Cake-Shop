package com.example.bakeryapp;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Selected_item extends AppCompatActivity {
    DrawerLayout drawerLayout2;
    ImageView hamburger;
    NavigationView nav2;
    TextView prod_name, prod_price,prod_description, total, discount, invalid;
    ImageView prod_img;
    EditText add1, add2, add3;
    BakeryProductsModel model;
    private String userId, lane1, lane2, lane3, address, thisDate, key;
    int offer = 0;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.menu_cart);

        userId = getIntent().getStringExtra("UserID");
        nav2 = (NavigationView) findViewById(R.id.navmenu);

        drawerLayout2 = (DrawerLayout) findViewById(R.id.drawerLayout2);
        hamburger = (ImageView) findViewById(R.id.hamburger);

        total = findViewById(R.id.total);
        discount = findViewById(R.id.discount);
        invalid = findViewById(R.id.invalid);
        add1 = findViewById(R.id.add1);
        add2 = findViewById(R.id.add2);
        add3 = findViewById(R.id.add3);

        nav2.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_home :
                        Intent intent  = new Intent(Selected_item.this, HomeScreenActivity.class);
                        startActivity(intent);
                        drawerLayout2.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_aboutus :
                        Intent intent2  = new Intent(Selected_item.this, About_us.class);
                        startActivity(intent2);
                        drawerLayout2.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_call :
                        Intent intent3  = new Intent(Selected_item.this, Contact_us.class);
                        startActivity(intent3);
                        drawerLayout2.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_logout :
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Selected_item.this);
                        builder1.setMessage("Are you sure you want to logout?");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent4  = new Intent(Selected_item.this, LoginActivity.class);
                                        startActivity(intent4);
                                        drawerLayout2.closeDrawer(GravityCompat.START);
                                        dialog.cancel();
                                    }
                                });

                        builder1.setNegativeButton(
                                "No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                        break;
                }

                return true;
            }
        });

        hamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout2.openDrawer(Gravity.LEFT);
            }
        });

        model = getIntent().getParcelableExtra("SelectedItem");
        prod_name = findViewById(R.id.prod_name);
        prod_price = findViewById(R.id.prod_price);
        prod_img = findViewById(R.id.prod_img);
        prod_description = findViewById(R.id.prod_description);
        prod_name.setText(model.getProductName());
        prod_price.setText("Prize ₹"+ model.getProductPrice());
        prod_description.setText(model.getProductDescription());
        prod_img.setImageDrawable(ContextCompat.getDrawable(this, model.getProductImage()));
        total.setText(model.getProductPrice());

        discount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String price = total.getText().toString();
                int val = Integer.parseInt(price);
                if (offer == 0){
                    if (val >= 500) {
                        double val1 = val - (val * 0.1);
                        total.setText(val1 + " ");
                        invalid.setText("Discount is Applicable");
                        offer = offer + 1;
                        invalid.setTextColor(Color.parseColor("#008000"));
                    } else {
                        invalid.setText("Not applicable for order below 500");
                        invalid.setTextColor(Color.parseColor("#ff0000"));
                    }
                }
                else{
                    invalid.setText("You can apply Only one Offer at a time.");
                    invalid.setTextColor(Color.parseColor("#ff0000"));
                }
            }
        });

        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        thisDate = currentDate.format(todayDate);
    }

    public void address(View view) {
        lane1 = add1.getText().toString();
        lane2 = add2.getText().toString();
        lane3 = add3.getText().toString();
        address = lane1 + lane2 +lane3;
        Toast.makeText(Selected_item.this, "Address saved successfully", Toast.LENGTH_SHORT).show();
    }
    public void buy(View view) {

       if (isConnectingToInternet()){
            //FirebaseDatabase database = FirebaseDatabase.getInstance();
            //myRef = database.getReference("Orders");
           //DatabaseReference child2 = myRef.child(userId);
           //child2.child("date").push().setValue(thisDate);
           //child2.child("product").push().setValue(model.getProductName());
           //child2.child("price").push().setValue(model.getProductPrice());
           //child2.child("address").push().setValue(address);
            Intent intent  = new Intent(Selected_item.this, Payment_Gateway.class);
            startActivity(intent);
        }
       else{
            Intent intent  = new Intent(Selected_item.this, No_internet.class);
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