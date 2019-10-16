package com.example.dmrcconnect;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import org.w3c.dom.Text;

public class SubmitComplaintFragment extends Fragment {
    int regardingID; //0 = train, 1=station, 2 = other
    String regarding = null;
    String coachNo = null; //if 0
    String stationName = null; //if 1
    String stationLine = null; //if 1
    String description = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        regardingID = getArguments().getInt("regardingID");
        if(regardingID==0){
            regarding = "Complaint regarding Metro Train";
            coachNo = getArguments().getString("coachNo");
        }
        else if(regardingID==1){
            regarding = "Complaint regarding Metro Station";
            stationName = getArguments().getString("stationName");
            stationLine = getArguments().getString("stationLine");

        }
        else if(regardingID==2){
            regarding = "Other complaint";
        }

        description = getArguments().getString("description");

        return inflater.inflate(R.layout.fragment_submit_complaint, container, false);

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

        LinearLayout l1 = (LinearLayout)view.findViewById(R.id.Layout_summaryTrain);
        LinearLayout l2 = (LinearLayout)view.findViewById(R.id.Layout_summaryStation);

        l1.setVisibility(View.INVISIBLE);
        l2.setVisibility(View.INVISIBLE);

        TextView tv_regarding = (TextView)view.findViewById(R.id.textView_regarding);
        tv_regarding.setText(regarding);

        TextView tv_description = (TextView)view.findViewById(R.id.textView_complaintDetails);
        tv_description.setText("Description: "+description);



        if(regardingID==0){
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.INVISIBLE);

            TextView tv_coach = (TextView)view.findViewById(R.id.textView_coachNumber);
            tv_coach.setText("Coach Number: "+coachNo);

        }
        else if(regardingID==1){
            l2.setVisibility(View.VISIBLE);
            l1.setVisibility(View.INVISIBLE);

            TextView tv_station = (TextView)view.findViewById(R.id.textView_stationName);
            tv_station.setText("Station: "+stationName);

            TextView tv_line = (TextView)view.findViewById(R.id.textView_stationLine);
            tv_line.setText("Line: "+stationLine);


        }

        ImageView back_button = getActivity().findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FormFragment();
                AHBottomNavigation bottom_nav = getActivity().findViewById(R.id.bottom_navigation);
                bottom_nav.setCurrentItem(2);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        final LinearLayout l = (LinearLayout) view.findViewById(R.id.Layout_trackChecked);
        CheckBox cb_track = (CheckBox) view.findViewById(R.id.checkBox_trackStatus);
        cb_track.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                   if(isChecked==true){
                       l.setVisibility(View.VISIBLE);
                   }
                   else{
                       l.setVisibility(View.INVISIBLE);
                   }

               }
           }
        );


        Button nextComplaints = (Button)view.findViewById(R.id.button_nextComplaints);
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
