package com.example.dmrcconnect;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FAQFragment extends Fragment {


    ArrayList<FAQCategory> faq_categories;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_faq, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        LinearLayout actionBarLayout = getActivity().findViewById(R.id.action_bar);
        AHBottomNavigation bottom_nav = getActivity().findViewById(R.id.bottom_navigation);
        if (bottom_nav.getCurrentItem() != 1) {
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
        if (bottom_nav.getCurrentItem() != 1) {
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
        if (bottom_nav.getCurrentItem() != 1) {
            actionBarLayout.setVisibility(View.VISIBLE);
        }

        LinearLayout floatingActionButtonLayout = getActivity().findViewById(R.id.floating_action_button_layout);
        floatingActionButtonLayout.setVisibility(View.GONE);

        query_for_faqs(view);

        ImageView back_button = getActivity().findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
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


    }

    public void query_for_faqs(final View view) {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        faq_categories = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url_for_categories = getString(R.string.db_url).concat("faqs/categories");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url_for_categories, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray categories_json = (JSONArray) response.get("result");

                            progressDialog.dismiss();

                            for (int i = 0; i < categories_json.length(); i++) {

                                JSONObject jsonObject = categories_json.getJSONObject(i);

                                String name = (String) jsonObject.get("name");
                                String description = (String) jsonObject.get("description");

                                faq_categories.add(new FAQCategory(name, description));
                            }

                        } catch (Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        }

                        populate_categories_list(view);


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        queue.add(jsonObjectRequest);
    }

    public void populate_categories_list(View view) {

        LayoutInflater inflater = getLayoutInflater();
        LinearLayout category_list = view.findViewById(R.id.faq_scrolling_list);

        for (int i = 0; i < faq_categories.size(); i++) {

            final FAQCategory faqCategory = faq_categories.get(i);
            LinearLayout category_card = (LinearLayout) inflater.inflate(R.layout.inflater_faq_card, null);
            TextView faq_category_title = (TextView) category_card.findViewById(R.id.faq_category_title);
            TextView faq_category_description = (TextView) category_card.findViewById(R.id.faq_category_description);

            faq_category_title.setText(faq_categories.get(i).getTitle());
            faq_category_description.setText(faq_categories.get(i).getDescription());

            category_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = null;
                    fragment = new HelpCentreFragment();

                    Bundle args = new Bundle();
                    args.putString("FAQ_Name", faqCategory.getTitle());
                    fragment.setArguments(args);


                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).
                            addToBackStack(null).commit();

                }
            });

            category_list.addView(category_card);
        }
    }


}
