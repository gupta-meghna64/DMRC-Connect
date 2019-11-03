package com.example.dmrcconnect;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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

public class HomeFragment extends Fragment {

    ArrayList<Announcement> recent_announcements;

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

        query_for_announcements(view);


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


    }

    public void query_for_announcements(final View view) {

        recent_announcements = new ArrayList<>();

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String url_for_announcements = getString(R.string.db_url).concat("announcements");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_for_announcements, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray categories_json = (JSONArray) response.get("result");

                    progressDialog.hide();
                    for (int i = 0; i < categories_json.length(); i++) {

                        JSONObject jsonObject = categories_json.getJSONObject(i);

                        String line = (String) jsonObject.get("line");
                        String description = (String) jsonObject.get("text");
                        String id = Integer.toString((Integer) jsonObject.get("id"));
                        String timestamp = (String) jsonObject.get("time");

                        recent_announcements.add(new Announcement(id, description, "", line, timestamp));
                    }

                    setup_announcements(view);
                } catch (Exception e) {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                progressDialog.hide();
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);

    }

    public void setup_announcements(View view) {
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout announcement_list = view.findViewById(R.id.home_announcement_scrolling_list);

        for (int i = 0; i < 3; i++) {

            LinearLayout announcement_card = (LinearLayout) inflater.inflate(R.layout.inflater_announcement_card, null);
            TextView announcement_title = (TextView) announcement_card.findViewById(R.id.announcement_title);
            TextView announcement_timestamp = (TextView) announcement_card.findViewById(R.id.announcement_timestamp);

            View affected_line = announcement_card.findViewById(R.id.announcement_line);

            announcement_title.setText(recent_announcements.get(i).getTitle());
            announcement_timestamp.setText(recent_announcements.get(i).getTimestamp());

            String line_color = recent_announcements.get(i).getLine();
            if (line_color.equals("Yellow")) {
                affected_line.setBackgroundColor(getResources().getColor(R.color.yellow_line));
            } else if (line_color.equals("Red")) {
                affected_line.setBackgroundColor(getResources().getColor(R.color.red_line));
            } else if (line_color.equals("Green")) {
                affected_line.setBackgroundColor(getResources().getColor(R.color.green_line));
            } else if (line_color.equals("Blue")) {
                affected_line.setBackgroundColor(getResources().getColor(R.color.blue_line));
            } else if (line_color.equals("Violet")) {
                affected_line.setBackgroundColor(getResources().getColor(R.color.violet_line));
            } else if (line_color.equals("Magenta")) {
                affected_line.setBackgroundColor(getResources().getColor(R.color.magenta_line));
            } else if (line_color.equals("Pink")) {
                affected_line.setBackgroundColor(getResources().getColor(R.color.pink_line));
            } else if (line_color.equals("Grey")) {
                affected_line.setBackgroundColor(getResources().getColor(R.color.grey_line));
            } else if (line_color.equals("Orange")) {
                affected_line.setBackgroundColor(getResources().getColor(R.color.orange_line));
            } else if (line_color.equals("Aqua")) {
                affected_line.setBackgroundColor(getResources().getColor(R.color.aqua_line));
            } else {
                affected_line.setBackgroundColor(getResources().getColor(R.color.default_line));
            }


            announcement_list.addView(announcement_card);
        }
    }
}
