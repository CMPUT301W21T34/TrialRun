package com.example.trialsinthewild;

import java.lang.reflect.Executable;
import java.security.acl.Owner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by tutlane on 19-12-2017.
 */

public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabsAdapter(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                OwnerFragment owner = new OwnerFragment();
                return owner;
            case 1:
                ExperimenterFragment exp = new ExperimenterFragment();
                return exp;
            default:
                return null;
        }
    }
}