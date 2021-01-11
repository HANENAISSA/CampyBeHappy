package com.example.campybehappy.Controller.Pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.campybehappy.Controller.Adapters.ListCampFavoriteAdapter;
import com.example.campybehappy.Global.Constants;
import com.example.campybehappy.Model.Camp;
import com.example.campybehappy.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ListCampFavoriteAdapter.OnCampListener {
    //drawer menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    LinearLayout contentView;

    // list camps adapter
    private ListCampFavoriteAdapter pad;
    private RecyclerView.LayoutManager LM;
    private RecyclerView rv;
    private List<Camp> lstCamp = new ArrayList<Camp>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // menu hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        contentView = findViewById(R.id.content);

        //call function
        navigationDrawer();
        // list camps
        lstCamp.add(new Camp("nom","soustitre"));
        lstCamp.add(new Camp("nom","soustitre"));
        lstCamp.add(new Camp("nom","soustitre"));
        lstCamp.add(new Camp("nom","soustitre"));
        lstCamp.add(new Camp("nom","soustitre"));
        lstCamp.add(new Camp("nom","soustitre"));


        rv= findViewById(R.id.recyclerview_favCamp);
        LM=new LinearLayoutManager(this);
        rv.setLayoutManager(LM);
        pad=new ListCampFavoriteAdapter(lstCamp, FavoriteActivity.this,this);
        rv.setAdapter(pad);
        DividerItemDecoration d=new DividerItemDecoration(rv.getContext(),DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(d);
    }

    /* Navigation Drawer functions */
    private void navigationDrawer() {
        // Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.myblue,getTheme()));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - Constants.END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent i = new Intent(FavoriteActivity.this,HomeActivity.class);
                startActivity(i);
                break;
            case R.id.nav_search:
                Intent i1 = new Intent(FavoriteActivity.this,SearchActivity.class);
                startActivity(i1);
                break;
            case R.id.nav_addCamp:
                Intent i2 = new Intent(FavoriteActivity.this,AddCampActivity.class);
                startActivity(i2);
                break;
            case R.id.nav_favorite:
                Intent i3 = new Intent(FavoriteActivity.this,FavoriteActivity.class);
                startActivity(i3);
                break;
            case R.id.nav_history:
                Intent i4 = new Intent(FavoriteActivity.this,HistoryActivity.class);
                startActivity(i4);
                break;
            case R.id.nav_profile:
                Intent i5 = new Intent(FavoriteActivity.this,ProfileActivity.class);
                startActivity(i5);
                break;
            case R.id.nav_logout:
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure to Logout ?")
                        .setMessage("Clicking Yes will make u redirected to Login page")
                        //set positive button
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SharedPreferences sharedPreferences = getSharedPreferences(Constants.MY_PREFS, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean(Constants.ISCONNECTED, false);
                                editor.apply();
                                Intent i6 = new Intent(FavoriteActivity.this,LoginActivity.class);
                                startActivity(i6);
                                finish();
                            }
                        })
                        //set negative button
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //nothing happen
                            }
                        })
                        .show();
                break;
        }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    /* end navigation drawer functions */

    /* Start top ToolBar functions */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.developpers) {
            Toast.makeText(FavoriteActivity.this, "@Copyright Aissa Hanen & Jbeli Mohamed Ali", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public void onNoteClick(int position) {
        Intent i = new Intent(FavoriteActivity.this, DetailsCampActivity.class);
        startActivity(i);
    }

    /* End top ToolBar functions */

}