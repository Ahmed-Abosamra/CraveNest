package com.example.cravenest;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        BottomNavigationView bottomNavigationView;
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        bottomNavigationView = findViewById(R.id.navbar);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout , new HomeFragment()).commit();
        }

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id= menuItem.getItemId();

                if(id == R.id.homeFragment2){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout , new HomeFragment()).commit();
                }
                else if(id == R.id.profileFragment){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout , new ProfileFragment()).commit();
                }
                else if (id == R.id.navigation_logout) {
                    startActivity(new Intent(Home.this, MainActivity.class));
                }

                return true;
            }
        });

    }
}