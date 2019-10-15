package com.example.dmrcconnect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CardView announcement_card = view.findViewById(R.id.announcement_card);
        announcement_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnnouncementsFragment fragment = new AnnouncementsFragment();
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
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
