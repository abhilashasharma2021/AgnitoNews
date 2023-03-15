package com.agnitonews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agnitonews.activity.CategoryActivity;
import com.agnitonews.activity.SettingActivity;
import com.agnitonews.fragment.DataFragment;
import com.agnitonews.fragment.HomeFrag;
import com.agnitonews.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.rom4ek.arcnavigationview.ArcNavigationView;

public class MainActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener,  NavigationView.OnNavigationItemSelectedListener {
    public static DrawerLayout drawerlayout;
    BottomNavigationView bottomNav;
    NavigationView  nav_view;
    RelativeLayout rlProfile,rlHome,rlSetting;
    ImageView ivCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottomNav);
        drawerlayout = findViewById(R.id.drawerlayout);
        nav_view = findViewById(R.id.nav_view);
        rlProfile = findViewById(R.id.rlProfile);
        rlSetting = findViewById(R.id.rlSetting);
        rlHome = findViewById(R.id.rlHome);
        ivCancel = findViewById(R.id.ivCancel);

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerlayout.closeDrawer(GravityCompat.START);
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFrag()).commit();
        }

        bottomNav.setOnNavigationItemSelectedListener(this);



        rlHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFrag()).commit();
                drawerlayout.closeDrawer(GravityCompat.START);
            }
        });
        rlProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFrag()).commit();
                drawerlayout.closeDrawer(GravityCompat.START);*/
            }
        });

        rlSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivity.this,SettingActivity.class));
                drawerlayout.closeDrawer(GravityCompat.START);
            }
        });

    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_home:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFrag()).commit();
                drawerlayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_profile:

              /*  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                drawerlayout.closeDrawer(GravityCompat.START);*/
                break;

        }
        return true;
    }
}