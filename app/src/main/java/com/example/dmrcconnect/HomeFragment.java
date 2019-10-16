package com.example.dmrcconnect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        LinearLayout actionBarLayout = getActivity().findViewById(R.id.action_bar);
        actionBarLayout.setVisibility(View.GONE);
        TextView fragment_title_textview = getActivity().findViewById(R.id.fragment_title);
        fragment_title_textview.setVisibility(View.GONE);

        LinearLayout floatingActionButtonLayout = getActivity().findViewById(R.id.floating_action_button_layout);
        floatingActionButtonLayout.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        LinearLayout actionBarLayout = getActivity().findViewById(R.id.action_bar);
        actionBarLayout.setVisibility(View.GONE);
        TextView fragment_title_textview = getActivity().findViewById(R.id.fragment_title);
        fragment_title_textview.setVisibility(View.GONE);

        LinearLayout floatingActionButtonLayout = getActivity().findViewById(R.id.floating_action_button_layout);
        floatingActionButtonLayout.setVisibility(View.GONE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout actionBarLayout = getActivity().findViewById(R.id.action_bar);
        AHBottomNavigation bottom_nav = getActivity().findViewById(R.id.bottom_navigation);
        if(bottom_nav.getCurrentItem() == 1){
            actionBarLayout.setVisibility(View.GONE);
            TextView fragment_title_textview = view.findViewById(R.id.fragment_title);
            fragment_title_textview.setVisibility(View.GONE);
        }
        else{
            actionBarLayout.setVisibility(View.VISIBLE);
        }

        LinearLayout floatingActionButtonLayout = getActivity().findViewById(R.id.floating_action_button_layout);
        floatingActionButtonLayout.setVisibility(View.GONE);


        CardView announcement_card = view.findViewById(R.id.announcement_card);
        announcement_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnnouncementsFragment fragment = new AnnouncementsFragment();
                AHBottomNavigation bottomNavigation = (AHBottomNavigation) getActivity().findViewById(R.id.bottom_navigation);
                bottomNavigation.setCurrentItem(0);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        CardView help_centre_card = view.findViewById(R.id.help_centre_card);
        help_centre_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpCentreFragment fragment = new HelpCentreFragment();
                AHBottomNavigation bottomNavigation = (AHBottomNavigation) getActivity().findViewById(R.id.bottom_navigation);
                bottomNavigation.setCurrentItem(2);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        CardView announcement_info_card1 = view.findViewById(R.id.announcement_info_card);
        announcement_info_card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnnouncementsFragment fragment = new AnnouncementsFragment();
                AHBottomNavigation bottomNavigation = (AHBottomNavigation) getActivity().findViewById(R.id.bottom_navigation);
                bottomNavigation.setCurrentItem(0);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
        CardView announcement_info_card2 = view.findViewById(R.id.announcement_info_card2);
        announcement_info_card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnnouncementsFragment fragment = new AnnouncementsFragment();
                AHBottomNavigation bottomNavigation = (AHBottomNavigation) getActivity().findViewById(R.id.bottom_navigation);
                bottomNavigation.setCurrentItem(0);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
