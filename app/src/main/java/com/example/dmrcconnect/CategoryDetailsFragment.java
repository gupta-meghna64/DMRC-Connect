package com.example.dmrcconnect;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryDetailsFragment extends Fragment {

    private String unique_id;
    public ArrayList<CategoryDetails> categoryDetails;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        unique_id = getArguments().getString("Category_ID");
        return inflater.inflate(R.layout.fragment_category_description, container, false);
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout actionBarLayout = getActivity().findViewById(R.id.action_bar);
        AHBottomNavigation bottom_nav = getActivity().findViewById(R.id.bottom_navigation);
        if (bottom_nav.getCurrentItem() != 1) {
            actionBarLayout.setVisibility(View.VISIBLE);
        }

        LinearLayout floatingActionButtonLayout = getActivity().findViewById(R.id.floating_action_button_layout);
        floatingActionButtonLayout.setVisibility(View.GONE);

        query_for_details(view, unique_id);


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

    public void query_for_details(final View view, String category_id) {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        categoryDetails = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        String url_for_details = getString(R.string.db_url).concat("frequent_complaints/").concat(category_id);
        progressDialog.dismiss();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url_for_details, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray details_json = (JSONArray) response.get("result");
                            for (int i = 0; i < details_json.length(); i++) {

                                JSONObject jsonObject = details_json.getJSONObject(i);

                                String title = (String) jsonObject.get("title");
                                String description = (String) jsonObject.get("description");
                                String id = Integer.toString((Integer) jsonObject.get("id"));
                                String type = (String) jsonObject.get("location_type");

                                categoryDetails.add(new CategoryDetails(title, description, type, id));
                            }



                        } catch (Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        }

                        populate_details_list(view);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(jsonObjectRequest);
    }

    public void populate_details_list(View view) {

        LayoutInflater inflater = getLayoutInflater();
        LinearLayout category_specific_scrolling_list = (LinearLayout) view.findViewById(R.id.category_specific_scrolling_list);

        for (int i = 0; i < categoryDetails.size(); i++) {

            LinearLayout details_card = (LinearLayout) inflater.inflate(R.layout.inflater_details_card, null);

            TextView details_title = (TextView) details_card.findViewById(R.id.details_title);
            details_title.setText(categoryDetails.get(i).getTitle());

            final String prefilled_complaint_id = categoryDetails.get(i).getId();

            details_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FormFragment fragment = new FormFragment();

                    Bundle args = new Bundle();
                    args.putString("Complaint_ID", prefilled_complaint_id);
                    fragment.setArguments(args);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, fragment, "findThisFragment")
                            .addToBackStack(null)
                            .commit();
                }
            });

            category_specific_scrolling_list.addView(details_card);

        }

    }

}