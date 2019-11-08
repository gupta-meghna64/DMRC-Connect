package com.example.dmrcconnect;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrackComplaintFragment extends Fragment {

    ArrayList<Complaint> recent_track_complaints;
    private DatabaseHelper myDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_track_complaint, container, false);

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


        ImageView back_button = getActivity().findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpCentreFragment fragment = new HelpCentreFragment();
                AHBottomNavigation bottomNavigation = (AHBottomNavigation) getActivity().findViewById(R.id.bottom_navigation);
                bottomNavigation.setCurrentItem(1);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        myDB = new DatabaseHelper(getActivity());
        Cursor cursor = myDB.getAllData();
        String phone_number = "";
        while (cursor.moveToNext()){
            phone_number = cursor.getString(0);
        }

        query_for_track_complaints(view, phone_number);

    }

    public void query_for_track_complaints(final View view, String phone_number) {

        recent_track_complaints = new ArrayList<Complaint>();

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String url_for_track_complaints = getString(R.string.db_url).concat("track_complaints").concat("/".concat(Utils.decryptIt(phone_number)));

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_for_track_complaints, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray categories_json = (JSONArray) response.get("result");

                    progressDialog.hide();
                    for (int i = 0; i < categories_json.length(); i++) {

                        JSONObject jsonObject = categories_json.getJSONObject(i);

                        String id = (String) jsonObject.get("id");
                        String title = (String) jsonObject.get("text");
                        String status = Integer.toString((Integer) jsonObject.get("id"));
                        //String timestamp = (String) jsonObject.get("time");

                        recent_track_complaints.add(new Complaint(id, title, status));
                    }

                    setup_track_complaint(view, recent_track_complaints.size());
                } catch (Exception e) {
                    //Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                progressDialog.hide();
                //Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);

    }

    public void setup_track_complaint(View view, int size) {

        if(size == 0){
            LayoutInflater inflater = getLayoutInflater();
            LinearLayout track_complaint_list = view.findViewById(R.id.track_complaint_scrolling_list);
            LinearLayout track_complaint_card = (LinearLayout) inflater.inflate(R.layout.inflater_no_track_complaint, null);
            TextView track_complaint_title = (TextView) track_complaint_card.findViewById(R.id.complaint_title);
            TextView track_complaint_id = (TextView) track_complaint_card.findViewById(R.id.complaint_id);

            track_complaint_list.addView(track_complaint_card);

        }
        else {
            LayoutInflater inflater = getLayoutInflater();
            LinearLayout track_complaint_list = view.findViewById(R.id.track_complaint_scrolling_list);

            for (int i = 0; i < recent_track_complaints.size(); i++) {

                LinearLayout track_complaint_card = (LinearLayout) inflater.inflate(R.layout.inflater_track_complaint, null);
                TextView track_complaint_title = (TextView) track_complaint_card.findViewById(R.id.complaint_title);
                TextView track_complaint_id = (TextView) track_complaint_card.findViewById(R.id.complaint_id);
                TextView track_complaint_status = (TextView) track_complaint_card.findViewById(R.id.complaint_status);

                ImageView affected_line = track_complaint_card.findViewById(R.id.status_imageview);

                track_complaint_title.setText(recent_track_complaints.get(i).getTitle());
                track_complaint_id.setText(recent_track_complaints.get(i).getId());
                track_complaint_status.setText(recent_track_complaints.get(i).getStatus());

                String status = recent_track_complaints.get(i).getStatus();
                if (status.equals("submitted")) {
                    affected_line.setBackgroundResource(R.drawable.timer);
                } else if (status.equals("under_review")) {
                    affected_line.setBackgroundResource(R.drawable.timer);
                } else {
                    affected_line.setBackgroundResource(R.drawable.verified);
                }


                track_complaint_list.addView(track_complaint_card);
            }
        }
    }

}
