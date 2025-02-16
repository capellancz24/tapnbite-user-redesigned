    package com.example.tapnbite.UserFragment;

    import android.os.Bundle;
    import android.util.Log;
    import androidx.fragment.app.Fragment;
    import androidx.lifecycle.ViewModelProvider;
    import androidx.navigation.NavController;
    import androidx.navigation.fragment.NavHostFragment;
    import android.view.LayoutInflater;
    import android.view.Menu;
    import android.view.MenuInflater;
    import android.view.MenuItem;
    import android.view.View;
    import android.view.ViewGroup;

    import com.example.tapnbite.R;
    import com.example.tapnbite.ViewModel.SharedViewModel;
    import com.google.android.material.appbar.MaterialToolbar;
    import com.google.android.material.chip.Chip;
    import com.google.android.material.chip.ChipGroup;
    import com.google.android.material.snackbar.Snackbar;

    public class FilterFragment extends Fragment {

        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        private String mParam1;
        private String mParam2;
        private MaterialToolbar materialToolbar;
        private NavController navController;
        private ChipGroup chipGroupCanteen, chipGroupCategories;
        private View view;
        private SharedViewModel viewModel;
        private boolean isChanged = false;

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

            NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.nav_host_fragment);
            navController = navHostFragment.getNavController();

            materialToolbar = view.findViewById(R.id.materialToolbar);
            chipGroupCanteen = view.findViewById(R.id.chipGroupCanteen);

            materialToolbar.setNavigationOnClickListener(view -> {
                // Handle navigation icon click
                navController.navigateUp(); // Navigate back
            });

            materialToolbar.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()) {
                    case R.id.save_action:
                        // Handle menu item 1 click
                        Snackbar.make(view, "Filter applied", Snackbar.LENGTH_SHORT).show();

                        // Disable the save action and change its icon
                        menuItem.setEnabled(false);
                        menuItem.setIcon(R.drawable.ic_check_disabled);

                        navController.navigateUp();
                        return true;
                    default:
                        return false;
                }
            });

            // Initialize ViewModel
            viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

            // Other initialization code...
            // Set up chip group checked change listener
            chipGroupCanteen.setOnCheckedChangeListener((group, checkedId) -> {
                if (checkedId != View.NO_ID) {
                    Chip selectedChip = view.findViewById(checkedId);
                    if (selectedChip != null) {
                        // Update the ViewModel with the selected chip's text
                        viewModel.selectChip(selectedChip.getText().toString());
                        // Enable the save action and change its icon
                        materialToolbar.getMenu().findItem(R.id.save_action).setEnabled(true);
                        materialToolbar.getMenu().findItem(R.id.save_action).setIcon(R.drawable.ic_check_enabled);
                        isChanged = true;
                    }
                } else {
                    // Disable the save action and change its icon
                    materialToolbar.getMenu().findItem(R.id.save_action).setEnabled(false);
                    materialToolbar.getMenu().findItem(R.id.save_action).setIcon(R.drawable.ic_check_disabled);
                }
            });

            MenuItem saveActionItem = materialToolbar.getMenu().findItem(R.id.save_action);
            saveActionItem.setEnabled(false);
            saveActionItem.setIcon(R.drawable.ic_check_disabled); // Set initial icon


            goBackToHome();

            return view;
        }

        private void goBackToHome() {
            materialToolbar.setNavigationOnClickListener(v -> navController.navigateUp()); // Navigate back
        }


    }