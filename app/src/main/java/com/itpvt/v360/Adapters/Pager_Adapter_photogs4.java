package com.itpvt.v360.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itpvt.v360.Fragments.Female_photogs;
import com.itpvt.v360.Fragments.Male_photogs;


/**
 * Created by Itpvt on 03-Nov-17.
 */

public class Pager_Adapter_photogs4 extends FragmentPagerAdapter {

    public Pager_Adapter_photogs4(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                Female_photogs female_photogs = new Female_photogs();
                return female_photogs;
            case 1:

            Male_photogs male_photogs = new Male_photogs();
            return male_photogs;

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
