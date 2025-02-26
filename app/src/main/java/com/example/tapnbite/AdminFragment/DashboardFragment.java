package com.example.tapnbite.AdminFragment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.tapnbite.R;
import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class DashboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private ImageButton menu;
    private CardView users, analytics, transactions, records;
    private DrawerLayout drawerLayout;
    private NavigationView navigation_view;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        menu = view.findViewById(R.id.ibMenu);
        drawerLayout = view.findViewById(R.id.drawer_layout);
        users = view.findViewById(R.id.cvUsers);
        analytics = view.findViewById(R.id.cvAnalytics);
        transactions = view.findViewById(R.id.cvTransactions);
        records = view.findViewById(R.id.cvRecords);

        // Handle navigation item clicks
        navigation_view = view.findViewById(R.id.navigation_view);
        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_home:
                        // Handle home action
                        break;
                    case R.id.nav_user:
                        // Handle profile action
                        break;
                    case R.id.nav_item:
                        // Handle profile action
                        break;
                    case R.id.nav_store:
                        // Handle profile action
                        break;
                    case R.id.nav_setting:
                        // Handle profile action
                        break;
                    case R.id.nav_logout:
                        // Handle profile action
                        break;
                    // Add more cases as needed
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        menu.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START);
                    } else {
                        setEnabled(false);
                        requireActivity().onBackPressed();
                    }
                }
            });
        }
    }

}