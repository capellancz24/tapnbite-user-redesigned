package com.example.tapnbite.UserFragment;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.tapnbite.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class FilterFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private MaterialToolbar materialToolbar;
    private NavController navController;
    private ChipGroup chipGroupCanteen, chipGroupCategories;
    private MenuItem applyFilterMenuItem;
    private View view;

    public FilterFragment() {
        // Required empty public constructor
    }

    public static FilterFragment newInstance(String param1, String param2) {
        FilterFragment fragment = new FilterFragment();
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
        view = inflater.inflate(R.layout.fragment_filter, container, false);

        materialToolbar = view.findViewById(R.id.materialToolbar);
        chipGroupCanteen = view.findViewById(R.id.chipGroupCanteen);
        chipGroupCategories = view.findViewById(R.id.chipGroupCategories);

        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        chipListenerClicked();
        goBackToHome();

        return view;
    }

    private void goBackToHome() {
        materialToolbar.setNavigationOnClickListener(v -> navController.navigateUp()); // Navigate back
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.filter_bar_menu, menu); // Inflate the menu
        applyFilterMenuItem = menu.findItem(R.id.menu_apply_filter); // Get the menu item
        applyFilterMenuItem.setEnabled(false); // Disable the menu item initially
        applyFilterMenuItem.setIcon(R.drawable.ic_check_disabled); // Set initial icon to grey
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void chipListenerClicked() {
        // Set listener for canteen chip group
        chipGroupCanteen.setOnCheckedChangeListener((group, checkedId) -> {
            Log.d("FilterFragment", "Canteen chip selected: " + checkedId);
            updateApplyFilterButtonState(); // Update button state when canteen chip changes
        });

        // Set listener for food category chip group
        chipGroupCategories.setOnCheckedChangeListener((group, checkedId) -> {
            Log.d("FilterFragment", "Category chip selected: " + checkedId);
            updateApplyFilterButtonState(); // Update button state when category chip changes
            handleCategorySelection(checkedId);
        });
    }

    private void updateApplyFilterButtonState() {
        // Check if any chip is selected in either chip group
        boolean isCanteenSelected = chipGroupCanteen.getCheckedChipId() != -1;
        boolean isCategorySelected = chipGroupCategories.getCheckedChipId() != -1;

        // Enable the menu item if any chip is selected
        if (applyFilterMenuItem != null) {
            boolean isEnabled = isCanteenSelected || isCategorySelected;
            applyFilterMenuItem.setEnabled(isEnabled);

            // Set the icon based on the enabled state
            if (isEnabled) {
                applyFilterMenuItem.setIcon(R.drawable.ic_check_enabled); // Set to white icon
            } else {
                applyFilterMenuItem.setIcon(R.drawable.ic_check_disabled); // Set to grey icon
            }

            // Log the state for debugging
            Log.d("FilterFragment", "Canteen Selected: " + isCanteenSelected + ", Category Selected: " + isCategorySelected);
        }
    }

    private void handleCategorySelection(int checkedId) {
        // If "All" is selected, deselect all other chips and disable them
        if (checkedId == R.id.chipAll) {
            for (int i = 0; i < chipGroupCategories.getChildCount(); i++) {
                Chip chip = (Chip) chipGroupCategories.getChildAt(i);
                if (chip.getId() != R.id.chipAll) {
                    chip.setChecked(false); // Deselect other chips
                    chip.setEnabled(false); // Disable other chips
                }
            }
        } else {
            // If any other chip is selected, enable the "All" chip
            Chip allChip = view.findViewById(R.id.chipAll);
            allChip.setEnabled(true);
        }

        // If "All" is deselected, enable all other chips
        if (chipGroupCategories.getCheckedChipId() != R.id.chipAll) {
            for (int i = 0; i < chipGroupCategories.getChildCount(); i++) {
                Chip chip = (Chip) chipGroupCategories.getChildAt(i);
                chip.setEnabled(true); // Enable all chips
            }
        }
    }
}