package com.itpvt.v360.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itpvt.v360.Fragments.Female_Art;
import com.itpvt.v360.Fragments.Male_Art;

/**
 * Created by Itpvt on 18-Nov-17.
 */

public class Pager_Adapter_Art extends FragmentPagerAdapter {

    public Pager_Adapter_Art(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                Female_Art female_art = new Female_Art();
                return female_art;

            case 1:

                Male_Art male_art = new Male_Art();
                return male_art;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
