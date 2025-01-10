package com.example.cravenest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find views by their IDs
        TextView usernameView = view.findViewById(R.id.username);
        TextView emailView = view.findViewById(R.id.email);

        // Back button logic
        TextView backButton = view.findViewById(R.id.back);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Home.class);
            startActivity(intent);
        });

        // Load user data from SharedPreferences (make sure the key matches)
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "Default Username");
        String email = sharedPreferences.getString("email", "Default Email");

        // Set data to TextViews
        usernameView.setText("Welcome " + username);
        emailView.setText(email);
    }
}
