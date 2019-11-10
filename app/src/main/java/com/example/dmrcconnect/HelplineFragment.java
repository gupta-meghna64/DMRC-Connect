package com.example.dmrcconnect;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HelplineFragment extends Fragment {

    public static String FACEBOOK_URL = "https://www.facebook.com/officialdmrc";
    public static String FACEBOOK_PAGE_ID = "officialdmrc";
    String url = "https://www.instagram.com/officialdmrc/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_helpline, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView facebook_button = view.findViewById(R.id.facebook_button);
        ImageView google_button = view.findViewById(R.id.mail_button);
        ImageView instagram_button = view.findViewById(R.id.instagram_button);
        ImageView twitter_button = view.findViewById(R.id.twitter_button);

        CardView general_helpline = view.findViewById(R.id.general_helpline);
        CardView women_helpline = view.findViewById(R.id.women_helpline);
        CardView sos = view.findViewById(R.id.sos);

        general_helpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "155370"));
                startActivity(intent);
            }
        });

        women_helpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "01123415480"));
                startActivity(intent);
            }
        });

        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "155655"));
                startActivity(intent);
            }
        });

        facebook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(getContext());
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });

        twitter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                try {
                    // get the Twitter app if possible
                    getContext().getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=OfficialDMRC"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    // no Twitter app, revert to browser
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/OfficialDMRC"));
                }
                startActivity(intent);
            }
        });

        instagram_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_VIEW);
                try {

                    PackageManager pm = getContext().getPackageManager();
                    if (pm.getPackageInfo("com.instagram.android", 0) != null) {
                        if (url.endsWith("/")) {
                            url = url.substring(0, url.length() - 1);
                        }
                        final String username = url.substring(url.lastIndexOf("/") + 1);
                        // http://stackoverflow.com/questions/21505941/intent-to-open-instagram-user-profile-on-android
                        intent.setData(Uri.parse("http://instagram.com/_u/" + username));
                        intent.setPackage("com.instagram.android");
                    }
                } catch (PackageManager.NameNotFoundException ignored) {
                }
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        google_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","helpline@dmrc,org", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send email"));
            }
        });


    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

}
