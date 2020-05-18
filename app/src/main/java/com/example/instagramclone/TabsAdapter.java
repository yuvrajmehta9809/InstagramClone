package com.example.instagramclone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabsAdapter extends FragmentPagerAdapter {
    public TabsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public static String[] getTabNames() {
        return tabNames;
    }

    private static String[] tabNames = {"Profile","People","Share Pictures"};

    @NonNull
    @Override
    public Fragment getItem(int tabPosition) {

        switch (tabPosition){
            case 0:
                return new Profile();
            case 1:
                return new People();
            case 2:
                return new ShareImage();
                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return tabNames.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Profile";
            case 1:
                return "People";
            case 2:
                return "Share Images";
                default:
                    return null;
        }
    }
}
