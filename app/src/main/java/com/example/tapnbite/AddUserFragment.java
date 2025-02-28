package com.example.tapnbite;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddUserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button add;
    private TextInputEditText schoolId, customerId, fullName, nuEmail, password, pelletBalance;

    public AddUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddUserFragment newInstance(String param1, String param2) {
        AddUserFragment fragment = new AddUserFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        schoolId = view.findViewById(R.id.inputSchoolID);
        customerId = view.findViewById(R.id.inputCustomerID);
        fullName = view.findViewById(R.id.inputFullName);
        nuEmail = view.findViewById(R.id.inputEmail);
        password = view.findViewById(R.id.inputPassword);
        pelletBalance = view.findViewById(R.id.inputPelletBalance);
        add = view.findViewById(R.id.btnAdd);


        addNewUser();

        return view;
    }

    public void addNewUser() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataSchoolId = schoolId.getText().toString().trim();
                String dataCustomerId = customerId.getText().toString();
                String dataFullName = fullName.getText().toString();
                String dataEmail = nuEmail.getText().toString();
                String dataPassword = password.getText().toString();
                String dataPelletBalance = pelletBalance.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(getActivity());
                String url = "http://192.168.18.6/tapnbite/create.php";  // IP address & PHP file should be in XAMPP folder (etdocs)

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("New record created successfully")) {
                                    Snackbar.make(view, "User added successfully", Snackbar.LENGTH_LONG)
                                            .setAction("Dismiss", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    // Dismiss the snackbar
                                                    Snackbar snackbar = (Snackbar) v.getTag();
                                                    snackbar.dismiss();
                                                }
                                            })
                                            .addCallback(new Snackbar.Callback() {
                                                @Override
                                                public void onShown(Snackbar sb) {
                                                    // Set the snackbar as a tag on the action view so it can be dismissed later
                                                    sb.getView().findViewById(com.google.android.material.R.id.snackbar_action).setTag(sb);
                                                }
                                            })
                                            .show();
                                    clearTextFields();
                                } else {
                                    Log.e("Volley Error", "Error: " + response);
                                    Snackbar.make(view, response, Snackbar.LENGTH_LONG).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.getLocalizedMessage());
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("customerID", dataCustomerId);
                        paramV.put("schoolID", dataSchoolId);
                        paramV.put("fullName", dataFullName);
                        paramV.put("email", dataEmail);
                        paramV.put("password", dataPassword);
                        paramV.put("pelletBalance", dataPelletBalance);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }

    //needs improvement
    private boolean validateInputs() {
        boolean isValid = true;

        // Get input values
        String dataSchoolId = schoolId.getText().toString().trim();
        String dataCustomerId = customerId.getText().toString().trim();
        String dataFullName = fullName.getText().toString().trim();
        String dataEmail = nuEmail.getText().toString().trim();
        String dataPassword = password.getText().toString().trim();
        String dataPelletBalance = pelletBalance.getText().toString().trim();

        // Validation
        if (dataSchoolId.isEmpty()) {
            schoolId.setError("School ID is required");
            isValid = false;
        } else
            schoolId.setError(null); // Clear the error


        if (dataCustomerId.isEmpty()) {
            customerId.setError("Customer ID is required");
            isValid = false;
        } else
            customerId.setError(null); // Clear the error


        if (dataFullName.isEmpty()) {
            fullName.setError("Full Name is required");
            isValid = false;
        } else
            fullName.setError(null); // Clear the error


        if (dataEmail.isEmpty()) {
            nuEmail.setError("Email is required");
            isValid = false;
        } else
            nuEmail.setError(null); // Clear the error


        if (dataPassword.isEmpty()) {
            password.setError("Password is required");
            isValid = false;
        } else
            password.setError(null); // Clear the error


        if (dataPelletBalance.isEmpty()) {
            pelletBalance.setError("Pellet Balance is required");
            isValid = false;
        } else
            pelletBalance.setError(null); // Clear the error


        return isValid; // Return the validation result
    }

    public void clearTextFields() {
        schoolId.setText("");
        customerId.setText("");
        fullName.setText("");
        nuEmail.setText("");
        password.setText("");
        pelletBalance.setText("");
    }
}