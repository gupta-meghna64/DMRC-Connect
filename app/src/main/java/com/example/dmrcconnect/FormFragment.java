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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import static android.R.layout.*;

public class FormFragment extends Fragment {
    int regardingID = -1; //0 = train, 1=station, 2 = other

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView back_button = getActivity().findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MainComplaintFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });


        String[] stations = {"Nehru Place", "Govindpuri", "Mandi House", "Rajiv Chowk"};
        String[] lines = {"Yellow", "Violet", "Pink", "Aqua"};
        final LinearLayout l1 = (LinearLayout) view.findViewById(R.id.Layout_CoachDetails);
        final LinearLayout l2 = (LinearLayout) view.findViewById(R.id.Layout_StationDetails);
        final LinearLayout l3 = (LinearLayout) view.findViewById(R.id.Layout_otherDetails);



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
                if(checkedId==R.id.radioButton_metroTrain){
                    regardingID = 0;
                    l1.setVisibility(View.VISIBLE);
                    l2.setVisibility(View.INVISIBLE);
                    l3.setVisibility(View.INVISIBLE);

                }
                else if(checkedId==R.id.radioButton_metroStation){
                    regardingID = 1;
                    l2.setVisibility(View.VISIBLE);
                    l1.setVisibility(View.INVISIBLE);
                    l3.setVisibility(View.INVISIBLE);

                }
                else if(checkedId==R.id.radioButton_other){
                    regardingID = 2;
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
                fragment = new SubmitComplaintFragment();
                FragmentManager fragmentManager = getFragmentManager();

                String description = "";
                description = ((EditText)view.findViewById(R.id.editText_complaint)).getText().toString();

                if(regardingID ==-1){
                    Toast.makeText(getContext(),"Please select complaint category",Toast.LENGTH_SHORT).show();
                }

                else{
                    if(regardingID ==0){
                        String coachNo = "";
                        coachNo = ((EditText)view.findViewById(R.id.editText_coachNumber)).getText().toString();
                        if(coachNo.matches("")){
                            Toast.makeText(getContext(),"Please enter coach number",Toast.LENGTH_SHORT).show();
                        }
                        else if(description.matches("")){
                            Toast.makeText(getContext(),"Please enter complaint details",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Bundle args = new Bundle();
                            args.putInt("regardingID", regardingID);
                            args.putString("coachNo", coachNo);
                            args.putString("description", description);

                            fragment.setArguments(args);

                            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                        }

                    }
                    else if(regardingID ==1){
                        String stationLine = "";
                        stationLine = ((AutoCompleteTextView)view.findViewById(R.id.autoComplete_stationLine)).getText().toString();
                        String stationName = "";
                        stationName = ((AutoCompleteTextView)view.findViewById(R.id.autoComplete_stationName)).getText().toString();

                        if(stationLine.matches("") || stationLine.matches("")){
                            Toast.makeText(getContext(),"Please enter station details",Toast.LENGTH_SHORT).show();
                        }
                        else if(description.matches("")){
                            Toast.makeText(getContext(),"Please enter complaint details",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Bundle args = new Bundle();
                            args.putInt("regardingID", regardingID);
                            args.putString("stationLine", stationLine);
                            args.putString("stationName", stationName);
                            args.putString("description", description);

                            fragment.setArguments(args);

                            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                        }

                    }
                    else if(regardingID ==2){
                        if(description.matches("")){
                            Toast.makeText(getContext(),"Please enter complaint details",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Bundle args = new Bundle();
                            args.putInt("regardingID", regardingID);
                            args.putString("description", description);

                            fragment.setArguments(args);

                            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                        }


                    }
                }


            }
        });
    }


}
