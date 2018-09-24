package com.itpvt.v360.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itpvt.v360.Fragments.Femal_model;
import com.itpvt.v360.Fragments.Male_model;

/**
 * Created by Itpvt on 03-Nov-17.
 */

public class Pager_Adapter_model extends FragmentPagerAdapter {

    public Pager_Adapter_model(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                Femal_model femal_model = new Femal_model();
                return femal_model;

            case 1:

                Male_model male_model = new Male_model();
                return male_model;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}

