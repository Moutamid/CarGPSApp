package com.moutamid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.moutamid.car_gps_app.R;
import com.moutamid.car_gps_app.adapters.NotificationVPadapter;

public class commands_fragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    private Bundle savedState = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.commands_fragment,container,false);
        tabLayout = view.findViewById(R.id.tabsLayout);
        viewPager = view.findViewById(R.id.ProfileViewpager);
        if(savedInstanceState != null && savedState == null) {
            savedState = savedInstanceState.getBundle("state");
        }
        if(savedState != null) {
            refresh();
        }
        savedState = null;

        return view;
    }

    private void refresh() {
        if (isAdded()) {
            NotificationVPadapter caddieProfileVPadapter = new NotificationVPadapter(
                    getChildFragmentManager());
            caddieProfileVPadapter.addFragment(new all_commands_fragment(), "ALL");
            caddieProfileVPadapter.addFragment(new late_commands_fragment(), "LATE");

            viewPager.setAdapter(caddieProfileVPadapter);
            tabLayout.setupWithViewPager(viewPager);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    private Bundle saveState() { /* called either from onDestroyView() or onSaveInstanceState() */
        Bundle state = new Bundle();
        state.putCharSequence("state", "fragment");
        return state;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("state", (savedState != null) ? savedState : saveState());
    }

}
