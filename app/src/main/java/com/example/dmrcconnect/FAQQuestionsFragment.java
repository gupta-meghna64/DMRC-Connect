package com.example.dmrcconnect;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class FAQQuestionsFragment extends Fragment {

    private String faq_category;
    public ArrayList<FAQ> faqs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        faq_category = getArguments().getString("FAQ_Name");
        return inflater.inflate(R.layout.fragment_faq_description, container, false);
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

        query_for_details(view, faq_category);

        TextView faq_title = (TextView) view.findViewById(R.id.faq_title);
        faq_title.setText(faq_category);

        ImageView back_button = getActivity().findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAQFragment fragment = new FAQFragment();
                AHBottomNavigation bottomNavigation = (AHBottomNavigation) getActivity().findViewById(R.id.bottom_navigation);
                bottomNavigation.setCurrentItem(2);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    public void query_for_details(final View view, String faq_title) {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        faqs = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        String url_for_details = getString(R.string.db_url).concat("faqs/categories/").concat(faq_title.replace(" ","%20"));
        progressDialog.dismiss();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url_for_details, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray details_json = (JSONArray) response.get("result");
                            for (int i = 0; i < details_json.length(); i++) {

                                JSONObject jsonObject = details_json.getJSONObject(i);

                                String category = (String) jsonObject.get("category");
                                String question = (String) jsonObject.get("question");
                                String id = Integer.toString((Integer) jsonObject.get("id"));
                                String answer = (String) jsonObject.get("answer");

                                faqs.add(new FAQ(id, question, answer, category));
                            }



                        } catch (Exception e) {
                            progressDialog.dismiss();
                        }

                        populate_details_list(view);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        progressDialog.dismiss();
                    }
                });

        queue.add(jsonObjectRequest);
    }

    public void populate_details_list(View view) {

        LayoutInflater inflater = getLayoutInflater();
        LinearLayout questions_scrolling_list = (LinearLayout) view.findViewById(R.id.questions_scrolling_list);

        for (int i = 0; i < faqs.size(); i++) {

            LinearLayout question_card = (LinearLayout) inflater.inflate(R.layout.inflater_question_card, null);

            TextView question_title = (TextView) question_card.findViewById(R.id.faq_question);
            question_title.setText(faqs.get(i).getQuestion());

            final String question_id = faqs.get(i).getId();

            question_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FAQAnswerFragment fragment = new FAQAnswerFragment();

                    Bundle args = new Bundle();
                    args.putString("Question_ID", question_id);
                    args.putString("FAQ_Category", faq_category);
                    fragment.setArguments(args);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, fragment, "findThisFragment")
                            .addToBackStack(null)
                            .commit();
                }
            });

            questions_scrolling_list.addView(question_card);

        }

    }

}