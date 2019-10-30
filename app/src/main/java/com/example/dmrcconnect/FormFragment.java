package com.example.dmrcconnect;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.R.layout;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Size;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import static android.R.layout.*;

public class FormFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);

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


        String[] stations = {"AIIMS", "Airport", "Akshardham", "Alpha 1", "Anand Vihar", "Arjan Garh", "Arthala", "Ashok Park Main", "Ashram", "Azadpur", "Badarpur", "Badkhal Mor", "Bahadurgarh City", "Barakhambha Road", "Bata Chowk", "Bhikaji Cama Place", "Botanical Garden", "Brigadier Hoshiyar Singh", "Central Secretariat", "Chandni Chowk", "Chawri Bazar", "Chhatarpur", "Chirag Delhi", "Civil Liness", "Dabri Mor-Janakpuri South", "Dashrath Puri", "Delhi Aerocity", "Delhi Cantonment", "Delhi Gate", "Delta 1", "Depot", "Dhaula Kuan", "Dilshad Garden", "Durgabai Deshmukh South Campus", "Dwarka", "Dwarka Mor", "Dwarka Sector 10", "Dwarka Sector 11", "Dwarka Sector 12", "Dwarka Sector 13", "Dwarka Sector 14", "Dwarka Sector 21", "Dwarka Sector 8", "Dwarka Sector 9", "East Azad Nagar", "East Vinod Nagar - Mayur Vihar-2", "Escorts Mujesar", "ESI Hospital", "Faridabad Old", "Ghevra Metro station", "Ghitorni", "GNIDA Office", "Gokulpuri", "Golf Course", "Govind Puri", "Greater Kailash", "Green Park", "GTB Nagar", "Guru Dronacharya", "Haiderpur", "Harkesh Nagar", "Hauz Khas", "Hazrat Nizamuddin", "Hindon", "HUDA City Centre", "IFFCO Chowk", "IIT Delhi", "INA", "Inderlok", "Indraprastha", "IP Extension", "ITO", "Jaffrabad", "Jahangirpuri", "Jama Masjid", "Jamia Millia Islamia", "Janakpuri East", "Janakpuri West", "Jangpura", "Janpath", "Jasola Apollo", "Jasola Vihar Shaheen Bagh", "Jawaharlal Nehru Stadium", "Jhandewalan", "Jhilmil", "Johri Enclave", "Jor Bagh", "Kailash Colony", "Kalindi Kunj", "Kalkaji Mandir", "Kanhiya Nagar", "Karkarduma", "Karkarduma Court", "Karol Bagh", "Kashmere Gate", "Kaushambi", "Keshav Puram", "Khan Market", "Kirti Nagar", "Knowledge Park II", "Kohat Enclave", "Krishna Nagar", "Lajpat Nagar", "Lal Qila", "Laxmi Nagar", "Lok Kalyan Marg", "Madipur", "Majlis Park", "Major Mohit Sharma", "Malviya Nagar", "Mandawali - West Vinod Nagar", "Mandi House", "Mansarovar Park", "Maujpur-Babarpur", "Mayapuri", "Mayur Vihar I", "Mayur Vihar Extension", "Mayur Vihar Pocket I", "Mewala Maharajpur", "MG Road", "Model Town", "Mohan Estate", "Mohan Nagar", "Moolchand", "Moti Nagar", "Mundka", "Mundka Industrial Area", "Munirka", "Nangloi", "Nangloi Railway station", "Naraina Vihar", "Nawada", "Neelam Chowk Ajronda", "Nehru Enclave", "Nehru Place", "Netaji Subhash Place", "New Ashok Nagar", "New Delhi", "NHPC Chowk", "Nirman Vihar", "Noida City Centre", "Noida Electronic City", "Noida Sector 101", "Noida Sector 137", "Noida Sector 142", "Noida Sector 143", "Noida Sector 144", "Noida Sector 145", "Noida Sector 146", "Noida Sector 147", "Noida Sector 148", "Noida Sector 15", "Noida Sector 16", "Noida Sector 18", "Noida Sector 34", "Noida Sector 50", "Noida Sector 51", "Noida Sector 52", "Noida Sector 59", "Noida Sector 61", "Noida Sector 62", "Noida Sector 76", "Noida Sector 81", "Noida Sector 83", "NSEZ", "Okhla Bird Sanctuary", "Okhla NSIC", "Okhla Vihar", "Palam", "Panchsheel Park", "Pandit Shree Ram Sharma", "Pari Chowk", "Paschim Vihar East", "Paschim Vihar West", "Patel Chowk", "Patel Nagar", "Peera Garhi", "Pitam Pura", "Pragati Maidan", "Pratap Nagar", "Preet Vihar", "Pul Bangash", "Punjabi Bagh", "Punjabi Bagh West", "Qutub Minar", "R.K.Puram", "Raj Bagh", "Raja Nahar Singh", "Rajdhani Park", "Rajendra Place", "Rajiv Chowk", "Rajouri Garden", "Ramakrishna Ashram Marg", "Ramesh Nagar", "Rithala", "Rohini East", "Rohini Sector 18", "Rohini West", "Sadar Bazaar Cantonment", "Saket", "Samaypur Badli", "Sant Surdas", "Sarai", "Sarita Vihar", "Sarojini Nagar", "Satguru Ram Singh Marg", "Sector 28", "Seelampur", "Shadipur", "Shahdara", "Shaheed Nagar", "Shaheed Sthal", "Shakurpur", "Shalimar Bagh", "Shankar Vihar", "Shastri Nagar", "Shastri Park", "Shiv Vihar", "Shivaji Park", "Shivaji Stadium", "Shyam park", "Sikandarpur", "Sir Vishweshwaraiah Moti Bagh", "South Extension", "Subhash Nagar", "Sukhdev Vihar", "Sultanpur", "Surajmal Stadium", "Tagore Garden", "Terminal 1-IGI Airport", "Tikri Border", "Tikri Kalan", "Tilak Nagar", "Tis Hazari", "Trilokpuri Sanjay Lake", "Tughlakabad", "Udyog Bhawan", "Udyog Nagar", "Uttam Nagar East", "Uttam Nagar West", "Vaishali", "Vasant Vihar", "Vidhan Sabha", "Vinobapuri", "Vishwa Vidyalaya", "Welcome", "Yamuna Bank"};
        String[] lines = {"Yellow", "Violet", "Pink", "Aqua", "Green", "Red", "Blue", "Magenta", "Grey", "Orange"};

        final LinearLayout l1 = (LinearLayout) view.findViewById(R.id.Layout_CoachDetails);
        final LinearLayout l2 = (LinearLayout) view.findViewById(R.id.Layout_StationDetails);

        RadioGroup rG_complaint = (RadioGroup) view.findViewById(R.id.radioGroup_complaintCategory);
        rG_complaint.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radioButton_metroTrain){
                    l1.setVisibility(View.VISIBLE);
                    l2.setVisibility(View.GONE);

                }
                else if(checkedId==R.id.radioButton_metroStation){
                    l2.setVisibility(View.VISIBLE);
                    l1.setVisibility(View.GONE);

                }
                else if(checkedId==R.id.radioButton_other){
                    l1.setVisibility(View.GONE);
                    l2.setVisibility(View.GONE);
                }

            }
        });





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
