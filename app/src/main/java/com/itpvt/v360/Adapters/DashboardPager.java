package com.itpvt.v360.Adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itpvt.v360.Fragments.Events;
import com.itpvt.v360.Fragments.Gallery;
import com.itpvt.v360.Fragments.Home;


public class DashboardPager extends FragmentPagerAdapter {
    public DashboardPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                Home loginfragment = new Home();
                return loginfragment;
            case 1:
                Gallery signupfragment = new Gallery();
                return signupfragment;
            case 2:
                Events teacherSignupFragment = new Events();
                return teacherSignupFragment;

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
