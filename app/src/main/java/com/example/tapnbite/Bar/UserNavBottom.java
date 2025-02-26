package com.example.tapnbite.Bar;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.tapnbite.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserNavBottom extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bottom_navigation);

        bottomNavigationView = findViewById(R.id.bottom_nav_view);

        NavHostFragment navHostFragment = (NavHostFragment)
                getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController); // Use the correct variable

        // Set up a listener for destination changes
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            // Check if the current destination is the Search Fragment
            if (destination.getId() == R.id.allCategoriesFragment ||
                    destination.getId() == R.id.filterFragment ||
                    destination.getId() == R.id.searchFragment ||
                    destination.getId() == R.id.privacyPolicyFragment ||
                    destination.getId() == R.id.termsAndConditionFragment ||
                    destination.getId() == R.id.faqsFragment ||
                    destination.getId() == R.id.splashScreenFragment ||
                    destination.getId() == R.id.onBoarding1Fragment ||
                    destination.getId() == R.id.onBoarding2Fragment ||
                    destination.getId() == R.id.onBoarding3Fragment ||
                    destination.getId() == R.id.loginFragment ||
                    destination.getId() == R.id.registerFragment ||
                    destination.getId() == R.id.dashboardFragment ||
                    destination.getId() == R.id.successFragment
            )
            {
                bottomNavigationView.setVisibility(View.GONE);
            } else {
                bottomNavigationView.setVisibility(View.VISIBLE);
            }

        });

    }

}