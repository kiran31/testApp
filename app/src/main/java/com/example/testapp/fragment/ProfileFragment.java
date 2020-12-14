package com.example.testapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.testapp.R;
import com.example.testapp.activity.TimerActivity;

public class ProfileFragment extends Fragment {
 Button btnWinCertificate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        btnWinCertificate = view.findViewById(R.id.btnWinCertificate);
        btnWinCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TimerActivity.class));
            }
        });

        return view;
    }
}