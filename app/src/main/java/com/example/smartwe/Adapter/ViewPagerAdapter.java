package com.example.smartwe.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.smartwe.Fragment.NotificationSwipeFragment;
import com.example.smartwe.Fragment.RequestFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NotificationSwipeFragment();
            case 1:
                return new RequestFragment();
            default:
                return new NotificationSwipeFragment();
        }
    }

    @Override
    public int getCount() {
        //how many items will there be in our view pager
        return 2;
    }


    //setting up the title in the tab layout
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "NOTIFICATION";
        }
        else if (position == 1){
            title = "REQUESTS";
        }
        return title;
    }
}
