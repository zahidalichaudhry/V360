package com.itpvt.v360.Activities.Tour;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class TourDashboardPager extends FragmentPagerAdapter {

    public TourDashboardPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                TourHome tourHome = new TourHome();
                return tourHome;
            case 1:
                TourGallery tourGallery = new TourGallery();
                return tourGallery;
            case 2:
                TourEvents tourEvents = new TourEvents();
                return tourEvents;

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
