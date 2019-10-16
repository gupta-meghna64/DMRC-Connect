package com.example.dmrcconnect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CategoryDetailsFragment extends Fragment {

    private String value;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        value = getArguments().getString("Category_ID");
        return inflater.inflate(R.layout.fragment_category_description, container, false);
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


        LayoutInflater inflater = getLayoutInflater();

        LinearLayout category_specific_scrolling_list = (LinearLayout) view.findViewById(R.id.category_specific_scrolling_list);
        ArrayList<CategoryDetails> category_specific_details = query_for_details(value);

        for (int i=0; i<category_specific_details.size();i++) {

            LinearLayout details_card = (LinearLayout) inflater.inflate(R.layout.inflater_details_card, null);

            TextView details_title = (TextView) details_card.findViewById(R.id.details_title);
            details_title.setText(category_specific_details.get(i).getTitle());

            details_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FormFragment fragment = new FormFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, fragment, "findThisFragment")
                            .addToBackStack(null)
                            .commit();
                }
            });

            category_specific_scrolling_list.addView(details_card);

        }


        ImageView back_button = getActivity().findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComplaintsCategoryFragment fragment = new ComplaintsCategoryFragment();
                AHBottomNavigation bottomNavigation = (AHBottomNavigation) getActivity().findViewById(R.id.bottom_navigation);
                bottomNavigation.setCurrentItem(2);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button new_complaint_button = (Button) view.findViewById(R.id.create_complaint_btn);
        new_complaint_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormFragment fragment = new FormFragment();
                AHBottomNavigation bottomNavigation = (AHBottomNavigation) getActivity().findViewById(R.id.bottom_navigation);
                bottomNavigation.setCurrentItem(2);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    ArrayList<CategoryDetails> query_for_details(String category_id) {

        ArrayList<CategoryDetails> details = new ArrayList<>();

        details.add(new CategoryDetails("AC is not effective.", ""));
        details.add(new CategoryDetails("AC's water is leaking.", ""));
        details.add(new CategoryDetails("AC is cooling too much.", ""));

        return details;
    }

}
