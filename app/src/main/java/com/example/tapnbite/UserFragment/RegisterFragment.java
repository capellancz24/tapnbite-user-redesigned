package com.example.tapnbite.UserFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tapnbite.R;
import com.example.tapnbite.UserFragment.BlankFragment.CanteenStaffFragment;
import com.example.tapnbite.UserFragment.BlankFragment.CustomerFragment;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class RegisterFragment extends Fragment {

    private View view;
    private Button login;
    private ViewPager2 userReg;
    private CustomerFragment customerFragment;
    private CanteenStaffFragment canteenStaffFragment;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize fragments here to retain their state
        customerFragment = new CustomerFragment();
        canteenStaffFragment = new CanteenStaffFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);
        userReg = view.findViewById(R.id.vpUser);
        login = view.findViewById(R.id.btnLogin);
        login.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.navigateToLoginFragment));

        MaterialButtonToggleGroup toggleGroup = view.findViewById(R.id.toggleButton);
        toggleGroup.setSingleSelection(true);
        toggleGroup.check(R.id.btnCustomer);

        toggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                userReg.setCurrentItem(checkedId == R.id.btnCustomer ? 0 : 1, true);
            }
        });

        userReg.setAdapter(new MyPagerAdapter(this));

        return view;
    }

    private class MyPagerAdapter extends FragmentStateAdapter {
        public MyPagerAdapter(Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return position == 0 ? customerFragment : canteenStaffFragment;
        }

        @Override
        public int getItemCount() {
            return 2; // Two fragments
        }
    }
}