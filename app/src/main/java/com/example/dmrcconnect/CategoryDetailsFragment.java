package com.example.dmrcconnect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LayoutInflater inflater = getLayoutInflater();

        LinearLayout category_specific_scrolling_list = (LinearLayout) view.findViewById(R.id.category_specific_scrolling_list);
        ArrayList<CategoryDetails> category_specific_details = query_for_details(value);

        for (int i=0; i<category_specific_details.size();i++) {

            LinearLayout details_card = (LinearLayout) inflater.inflate(R.layout.inflater_details_card, null);

            TextView details_title = (TextView) details_card.findViewById(R.id.details_title);
            details_title.setText(category_specific_details.get(i).getTitle());

            category_specific_scrolling_list.addView(details_card);

        }

    }

    ArrayList<CategoryDetails> query_for_details(String category_id) {

        ArrayList<CategoryDetails> details = new ArrayList<>();

        details.add(new CategoryDetails("AC not working", ""));
        details.add(new CategoryDetails("AC water leaking", ""));
        details.add(new CategoryDetails("AC too cold", ""));
        details.add(new CategoryDetails("AC not effective", ""));

        return details;
    }

}
