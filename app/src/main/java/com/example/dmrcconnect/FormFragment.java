package com.example.dmrcconnect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.R.layout;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import static android.R.layout.*;

public class FormFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        String[] stations = {"Nehru Place", "Govindpuri", "Mandi House", "Rajiv Chowk"};
        String[] lines = {"Yellow", "Violet", "Pink", "Aqua"};
        final LinearLayout l1 = (LinearLayout) view.findViewById(R.id.Layout_CoachDetails);
        final LinearLayout l2 = (LinearLayout) view.findViewById(R.id.Layout_StationDetails);
        final LinearLayout l3 = (LinearLayout) view.findViewById(R.id.Layout_otherDetails);

        super.onViewCreated(view, savedInstanceState);

        AppCompatAutoCompleteTextView autoTextView_stations = (AppCompatAutoCompleteTextView) view.findViewById(R.id.autoComplete_stationName);
        ArrayAdapter<String> adapter_stations = new ArrayAdapter<String>
                (getContext(), android.R.layout.select_dialog_item, stations);
        autoTextView_stations.setThreshold(1); //will start working from first character
        autoTextView_stations.setAdapter(adapter_stations);

        AppCompatAutoCompleteTextView autoTextView_lines = (AppCompatAutoCompleteTextView) view.findViewById(R.id.autoComplete_stationLine);
        ArrayAdapter<String> adapter_lines = new ArrayAdapter<String>
                (getContext(), android.R.layout.select_dialog_item, lines);
        autoTextView_lines.setThreshold(1); //will start working from first character
        autoTextView_lines.setAdapter(adapter_lines);

        RadioGroup rG_complaint = (RadioGroup) view.findViewById(R.id.radioGroup_complaintCategory);
        rG_complaint.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton_metroTrain) {
                    l1.setVisibility(View.VISIBLE);
                    l2.setVisibility(View.INVISIBLE);
                    l3.setVisibility(View.INVISIBLE);

                } else if (checkedId == R.id.radioButton_metroStation) {
                    l2.setVisibility(View.VISIBLE);
                    l1.setVisibility(View.INVISIBLE);
                    l3.setVisibility(View.INVISIBLE);

                } else if (checkedId == R.id.radioButton_other) {
                    l3.setVisibility(View.VISIBLE);
                    l1.setVisibility(View.INVISIBLE);
                    l2.setVisibility(View.INVISIBLE);
                }

            }
        });

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


        Button nextComplaints = (Button) view.findViewById(R.id.button_nextComplaints);
        nextComplaints.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new ComplaintSuccessFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
    }


}
