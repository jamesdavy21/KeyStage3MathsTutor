package com.example.up804392.keystage3mathstutor.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.up804392.keystage3mathstutor.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        BottomNavigationView navView = view.findViewById(R.id.nav_bottom_view);
        navView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_quiz);
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeQuizFragment()).commit();
        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;

            switch (menuItem.getItemId()) {
                default:
                    fragment = new HomeQuizFragment();
                    break;
                case R.id.navigation_guide:
                    fragment = new HomeGuideFragment();
                    break;
                case R.id.navigation_game:
                    fragment = new HomeGameFragment();
                    break;
            }
            getChildFragmentManager().popBackStack();
            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
    };
}