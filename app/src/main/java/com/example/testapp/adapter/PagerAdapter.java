package com.example.testapp.adapter;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.testapp.fragment.CertificateFragment;
import com.example.testapp.fragment.ChatFragment;
import com.example.testapp.fragment.ProfileFragment;
import com.example.testapp.fragment.ScoreFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int noOfTabs;
    public PagerAdapter(FragmentManager fm, int noOfTabs) {
        super(fm);
        this.noOfTabs = noOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                CertificateFragment certificateFragment = new CertificateFragment();
                return  certificateFragment;

            case 1:
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
            case 2:
                ChatFragment chatFragment = new ChatFragment();
                return chatFragment;
            case 3:
                ScoreFragment scoreFragment = new ScoreFragment();
                return scoreFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
