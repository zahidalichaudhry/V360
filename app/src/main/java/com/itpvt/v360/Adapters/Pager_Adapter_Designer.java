package com.itpvt.v360.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itpvt.v360.Fragments.Female_Designer;
import com.itpvt.v360.Fragments.Male_Design;


/**
 * Created by Itpvt on 17-Nov-17.
 */

public class Pager_Adapter_Designer extends FragmentPagerAdapter {
    public Pager_Adapter_Designer(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                Female_Designer female_designer = new Female_Designer();
                return female_designer;
            case 1:
                Male_Design male_design = new Male_Design();
                return male_design;


            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
