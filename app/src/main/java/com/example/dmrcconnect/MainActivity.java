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
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home", R.drawable.home, R.color.secondaryTextColor);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Announcements", R.drawable.notification, R.color.secondaryTextColor);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Help Centre", R.drawable.question, R.color.secondaryTextColor);

        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item3);

        bottomNavigation.setAccentColor(getResources().getColor(R.color.primaryColor));
        bottomNavigation.setItemDisableColor(getResources().getColor(R.color.secondaryTextColor));
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FFFFFF"));
        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setForceTint(true);
        bottomNavigation.setTranslucentNavigationEnabled(true);
        bottomNavigation.setTitleTextSize(28, 28);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        bottomNavigation.setCurrentItem(1);
        Fragment fragment = null;
        fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (position == 0) {
                    Fragment fragment = null;
                    fragment = new AnnouncementsFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();

                } else if (position == 1) {
                    Fragment fragment = null;
                    fragment = new HomeFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();

                } else if (position == 2) {
                    Fragment fragment = null;
                    fragment = new HelpCentreFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
                }
                return true;
            }
        });

    }
}
