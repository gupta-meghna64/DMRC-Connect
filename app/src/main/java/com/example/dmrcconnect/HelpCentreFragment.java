package com.example.dmrcconnect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import java.util.ArrayList;

public class HelpCentreFragment extends Fragment {

    private ArrayList<HelpSearchResult> helpSearchResultArrayList;
    private AppCompatAutoCompleteTextView autoTextViewCustom;
    private SearchAdapter searchAdapter;
    private TextView search_desc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_helpcentre, container, false);

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout actionBarLayout = getActivity().findViewById(R.id.action_bar);
        AHBottomNavigation bottom_nav = getActivity().findViewById(R.id.bottom_navigation);
        if(bottom_nav.getCurrentItem() != 1){
            actionBarLayout.setVisibility(View.VISIBLE);
        }

        LinearLayout floatingActionButtonLayout = getActivity().findViewById(R.id.floating_action_button_layout);
        floatingActionButtonLayout.setVisibility(View.GONE);

        CardView complaint_card = (CardView) view.findViewById(R.id.complaint_card);
        complaint_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComplaintsCategoryFragment fragment = new ComplaintsCategoryFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        CardView helpline_card = view.findViewById(R.id.helpline_card);
        helpline_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelplineFragment fragment = new HelplineFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        CardView faq_card = view.findViewById(R.id.faq_card);
        faq_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAQFragment fragment = new FAQFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        CardView track_card = view.findViewById(R.id.guide_card);
        track_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrackComplaintFragment fragment = new TrackComplaintFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        ImageView back_button = getActivity().findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment fragment = new HomeFragment();
                AHBottomNavigation bottomNavigation = (AHBottomNavigation) getActivity().findViewById(R.id.bottom_navigation);
                bottomNavigation.setCurrentItem(1);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        autoTextViewCustom = (AppCompatAutoCompleteTextView) view.findViewById(R.id.searchBarHelpCentre);
        search_desc = (TextView) view.findViewById(R.id.help_result_name);


        helpSearchResultArrayList = new ArrayList<>();
        helpSearchResultArrayList.add(new HelpSearchResult(R.drawable.twitter, "AC in Complaints"));
        helpSearchResultArrayList.add(new HelpSearchResult(R.drawable.twitter, "AC in FAQ"));
        helpSearchResultArrayList.add(new HelpSearchResult(R.drawable.twitter, "Women's Helpline"));

        searchAdapter = new SearchAdapter(getContext(), R.layout.custom_row, helpSearchResultArrayList);
        autoTextViewCustom.setThreshold(1);
        autoTextViewCustom.setAdapter(searchAdapter);

        autoTextViewCustom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HelpSearchResult fruit = (HelpSearchResult) adapterView.getItemAtPosition(i);
                search_desc.setText(fruit.getName());
            }
        });






    }
}
