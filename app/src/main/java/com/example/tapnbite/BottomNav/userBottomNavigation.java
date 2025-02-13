package com.example.tapnbite.BottomNav;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.tapnbite.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class userBottomNavigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bottom_navigation);


        BottomNavigationView buttomNavigationView = findViewById(R.id.bottom_nav_view);

        NavHostFragment navHostFragment = (NavHostFragment)
                getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(buttomNavigationView, navController);
    }

}