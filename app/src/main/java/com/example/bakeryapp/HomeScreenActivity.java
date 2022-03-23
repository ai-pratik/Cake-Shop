package com.example.bakeryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeScreenActivity extends AppCompatActivity implements OnProductClickListner {
    DrawerLayout drawerLayout;
    ImageView hamburger;
    NavigationView nav;
    SearchView serachView;
    private RecyclerView recyclerView;
    RecyclerAdapter adapter;
    private ArrayList<BakeryProductsModel> bakeryProductsModelArrayList = new ArrayList<>();
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        userId = getIntent().getStringExtra("UserID");

        recyclerView = findViewById(R.id.recyclerView);
        serachView = findViewById(R.id.serachView);

        nav = (NavigationView) findViewById(R.id.navmenu);
        drawerLayout= (DrawerLayout)findViewById(R.id.drawerLayout);
        hamburger= (ImageView)findViewById(R.id.hamburger);


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.menu_home :
                        Intent intent  = new Intent(HomeScreenActivity.this, HomeScreenActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_aboutus :
                        Intent intent2  = new Intent(HomeScreenActivity.this, About_us.class);
                        startActivity(intent2);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_call :
                        Intent intent3  = new Intent(HomeScreenActivity.this, Contact_us.class);
                        startActivity(intent3);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_logout :
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(HomeScreenActivity.this);
                        builder1.setMessage("Are you sure you want to logout?");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent4  = new Intent(HomeScreenActivity.this, LoginActivity.class);
                                        startActivity(intent4);
                                        drawerLayout.closeDrawer(GravityCompat.START);
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
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        bakeryProductsModelArrayList.add(new BakeryProductsModel(R.drawable.blueberry,"Blueberry Cake","This is a nice tender cake - one of my Mom's specialties from years ago. It is a great cake to take along to a picnic.","350"));
        bakeryProductsModelArrayList.add(new BakeryProductsModel(R.drawable.blackforest,"Black Forest Cake","Surprise your beloved one on every occasion with this amazing Black Forest cake be it birthday, anniversary.","520"));
        bakeryProductsModelArrayList.add(new BakeryProductsModel(R.drawable.chocolatetruffle,"Chocolate Truffle Cake","Zest the flavours of this delicious cake with your mouth smudged with chocolate. It is a cake that will melt with every bite and will leave you enchanted by its taste.","450"));
        bakeryProductsModelArrayList.add(new BakeryProductsModel(R.drawable.lemoncheesecake,"Lemon Cheese Cake","This butterscotch cake It is a butter-based recipe with whipped eggs, which gives us a moist yet light and airy cake. It is a perfect cake to make for anyone who loves butterscotch","550"));
        bakeryProductsModelArrayList.add(new BakeryProductsModel(R.drawable.strawberrycheese,"Strawberry Cheese Cake","Smooth and creamy, it’s topped with delicious strawberries,and stawberries sauce.It is lip – smackingly delicious ,juicy cheesecake.","1000"));
        bakeryProductsModelArrayList.add(new BakeryProductsModel(R.drawable.cake1,"Chocolate Cake","Baked in deliciously rich cocoa powder, moist cake, freshly whipped chocolate cream & topped with truffle. This cake is the absolute solution to your desire, order today & indulge yourself in this heavenly treat.","450"));
        bakeryProductsModelArrayList.add(new BakeryProductsModel(R.drawable.pineapple,"Pineapple Cake","Absolutely irresistible, this yummy pineapple cake, garnished with delicious whipped cream ,red cherries and pineapple.","350"));
        bakeryProductsModelArrayList.add(new BakeryProductsModel(R.drawable.redvelvet,"Redvelvet Cake","Red velvet cake is traditionally a red, red-brown, crimson or scarlet-colored chocolate layer cake, layered with ermine icing","450"));
        bakeryProductsModelArrayList.add(new BakeryProductsModel(R.drawable.mangocheese,"Mango Cheese Cake","Yummy delicious mango cheese cake with full of cream cheese with a topping of mango chunks,res cherries..","600"));
        bakeryProductsModelArrayList.add(new BakeryProductsModel(R.drawable.fruitilicious,"Fruitilicious Cake","A fruit cake is fresh and light, a white sponge layered cake with multi-fruit flavoured cream. Blend of mixed fruit with toppings of fresh fruits on it.","850"));
        bakeryProductsModelArrayList.add(new BakeryProductsModel(R.drawable.rasmalai,"Rasmalai Cake","It has been described as \"a rich cheesecake without a crust\". The best thing about the cake is it has many dry fruits and their flavor which not only enhance the taste of cake but also add nutrition to it. ","599"));
        bakeryProductsModelArrayList.add(new BakeryProductsModel(R.drawable.oreocheesecake,"Oreo Cheese Cake","A traditional cheese cake with flavors of oero biscuits,having crunchy texture.Garnished with cream,oreo biscuits.","950"));
        bakeryProductsModelArrayList.add(new BakeryProductsModel(R.drawable.rainbow,"Rainbow Cake","A Rainbow cake is one of the most beautiful cakes.Nowadays designer cakes are in trend and Rainbow cake is one of them.","350"));
        LinearLayoutManager manager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(manager);
        adapter = new RecyclerAdapter(this,bakeryProductsModelArrayList, this);
        recyclerView.setAdapter(adapter);
        serachView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public void onProductClick(BakeryProductsModel model) {
        if (model!=null){
            Intent intent = new Intent(this, Selected_item.class);
            intent.putExtra("SelectedItem", model);
            intent.putExtra("UserID",userId);
            startActivity(intent);
        }
    }
}