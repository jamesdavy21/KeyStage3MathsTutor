package com.example.up804392.keystage3mathstutor.ui.guide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.up804392.keystage3mathstutor.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class GuideFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.nav_bottom_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        bottomNavigationView.setSelectedItemId(R.id.guide_about);
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_container, createFragment(new GuideAboutFragment())).commit();

        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = menuItem -> {
        Fragment fragment;

        if (menuItem.getItemId() == R.id.guide_example) {
            fragment = createFragment(new GuideExampleFragment());
        } else {
            fragment = createFragment(new GuideAboutFragment());
        }
        getChildFragmentManager().popBackStack();
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        return true;
    };

    private Fragment createFragment(Fragment fragment) {
        fragment.setArguments(getArguments());
        return fragment;
    }

}
