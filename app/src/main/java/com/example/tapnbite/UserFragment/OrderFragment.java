package com.example.tapnbite.UserFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapnbite.Class.Food;
import com.example.tapnbite.Class.OrderDetail;
import com.example.tapnbite.R;
import com.example.tapnbite.UserFragment.Adapter.OrderDetailAdapter;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerViewOrders;
    private OrderDetailAdapter adapter;
    private List<OrderDetail> orderList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        recyclerViewOrders = view.findViewById(R.id.recyclerViewOrders);
        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(getContext()));

        orderList = new ArrayList<>();
        loadOrders();

        adapter = new OrderDetailAdapter(orderList);
        recyclerViewOrders.setAdapter(adapter);

        return view;
    }
        /* Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);*/
        private void loadOrders() {
            List<Food> foodItems = new ArrayList<>();
            foodItems.add(new Food("101", "Burger", "Beef burger", "Fast Food", "Canteen 1", "Burger Shop", "10 min", 150, "burger.jpg"));
            foodItems.add(new Food("102", "Pizza", "Cheese Pizza", "Fast Food", "Canteen 2", "Pizza Hut", "15 min", 250, "pizza.jpg"));

            orderList.add(new OrderDetail("28", foodItems, "On Process", "C123", "March 3, 2025"));
            orderList.add(new OrderDetail("29", foodItems, "Pending Review", "C124", "March 3, 2025"));
        }

}


