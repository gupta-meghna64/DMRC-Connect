package com.example.dmrcconnect;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

public class MainActivity extends AppCompatActivity {

    public static final String COMPLAIN = "Complain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AHBottomNavigation bottomNavigation = findViewById(R.id.bottom_navigation);

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Announcements", R.drawable.ic_home_black_24dp, R.color.grey);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Complain", R.drawable.ic_home_black_24dp, R.color.grey);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Helpline", R.drawable.ic_home_black_24dp, R.color.grey);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("FAQs", R.drawable.ic_home_black_24dp, R.color.grey);

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);

        bottomNavigation.setAccentColor(getResources().getColor(R.color.colorPrimary));
        bottomNavigation.setItemDisableColor(getResources().getColor(R.color.grey));

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FFFFFF"));

        // Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

        // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

        // Display color under navigation bar (API 21+)
        // Don't forget these lines in your style-v21
        bottomNavigation.setTranslucentNavigationEnabled(true);

        // Manage titles
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        // Set current item programmatically
        bottomNavigation.setCurrentItem(0);
        Fragment fragment = null;
        fragment = new AnnouncementsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();


        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...
                if (position == 0) {
                    Fragment fragment = null;
                    fragment = new AnnouncementsFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                } else if (position == 1) {
                    Fragment fragment = null;
                    fragment = new ComplaintFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                } else if (position == 2) {
                    Fragment fragment = null;
                    fragment = new HelplineFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                } else {
                    Fragment fragment = null;
                    fragment = new FaqFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                }
                return true;
            }
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
                // Manage the new y position
            }
        });


    }
}
