package com.example.dmrcconnect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;


public class ComplaintSuccessFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_complaint_success, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();
        LinearLayout actionBarLayout = getActivity().findViewById(R.id.action_bar);
        AHBottomNavigation bottom_nav = getActivity().findViewById(R.id.bottom_navigation);
        if(bottom_nav.getCurrentItem() != 1){
            actionBarLayout.setVisibility(View.VISIBLE);
        }

        LinearLayout floatingActionButtonLayout = getActivity().findViewById(R.id.floating_action_button_layout);
        floatingActionButtonLayout.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        LinearLayout actionBarLayout = getActivity().findViewById(R.id.action_bar);
        AHBottomNavigation bottom_nav = getActivity().findViewById(R.id.bottom_navigation);
        if(bottom_nav.getCurrentItem() != 1){
            actionBarLayout.setVisibility(View.VISIBLE);
        }

        LinearLayout floatingActionButtonLayout = getActivity().findViewById(R.id.floating_action_button_layout);
        floatingActionButtonLayout.setVisibility(View.GONE);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout actionBarLayout = getActivity().findViewById(R.id.action_bar);
        AHBottomNavigation bottom_nav = getActivity().findViewById(R.id.bottom_navigation);
        if(bottom_nav.getCurrentItem() != 1){
            actionBarLayout.setVisibility(View.VISIBLE);
        }

        LinearLayout floatingActionButtonLayout = getActivity().findViewById(R.id.floating_action_button_layout);
        floatingActionButtonLayout.setVisibility(View.GONE);

        ImageView back_button = getActivity().findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainComplaintFragment fragment = new MainComplaintFragment();
                AHBottomNavigation bottomNavigation = (AHBottomNavigation) getActivity().findViewById(R.id.bottom_navigation);
                bottomNavigation.setCurrentItem(2);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

    }
}
