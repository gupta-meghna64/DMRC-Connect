package com.example.dmrcconnect;

import android.content.Intent;
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
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class ComplaintsCategoryFragment extends Fragment {


    String unique_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_complaint_categories, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LayoutInflater inflater = getLayoutInflater();
        LinearLayout category_list = view.findViewById(R.id.category_scrolling_list);

        final ArrayList<ComplaintCategory> frequent_categories = query_for_categories();

        for (int i = 0; i < frequent_categories.size(); i++) {

            LinearLayout category_card = (LinearLayout) inflater.inflate(R.layout.inflater_category_card, null);
            TextView category_title = (TextView) category_card.findViewById(R.id.category_title);
            TextView category_description = (TextView) category_card.findViewById(R.id.category_description);

            category_title.setText(frequent_categories.get(i).getTitle());
            category_description.setText(frequent_categories.get(i).getDescription());
            unique_id = frequent_categories.get(i).getUnique_id();

            category_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = null;
                    fragment = new CategoryDetailsFragment();

                    Bundle args = new Bundle();
                    args.putString("Category_ID", unique_id);
                    fragment.setArguments(args);


                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

                }
            });

            category_list.addView(category_card);
        }


    }

    ArrayList<ComplaintCategory> query_for_categories() {

        ArrayList<ComplaintCategory> categories = new ArrayList<>();
        categories.add(new ComplaintCategory("AC", "Report problems related to AC"));
        categories.add(new ComplaintCategory("Hygiene", "Report problems related to cleanliness and hygiene"));
        categories.add(new ComplaintCategory("Delays", "Report unexpected delays"));
        categories.add(new ComplaintCategory("Lost and Found", "Report lost items/items found"));
        categories.add(new ComplaintCategory("Discipline", "Report problems related to misconduct"));
        categories.add(new ComplaintCategory("Hygiene", "Report problems related to cleanliness and hygiene"));
        categories.add(new ComplaintCategory("Delays", "Report unexpected delays"));

        return categories;
    }
}
