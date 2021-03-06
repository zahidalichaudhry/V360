package com.itpvt.v360.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.itpvt.v360.Adapters.Pager_Adapter_photogs4;
import com.itpvt.v360.R;


public class All_photogs extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    Pager_Adapter_photogs4 pageradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_all_photogs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        pageradapter = new Pager_Adapter_photogs4(getSupportFragmentManager());
        viewPager.setAdapter(pageradapter);

        final TabLayout.Tab Male_Photogs = tabLayout.newTab();
        final TabLayout.Tab Femal_Photogs = tabLayout.newTab();

        Femal_Photogs.setText("Female");
        Male_Photogs.setText("Male");


        tabLayout.addTab(Femal_Photogs,0);
        tabLayout.addTab(Male_Photogs,1);

        tabLayout.setTabTextColors(
                ContextCompat.getColor(All_photogs.this, R.color.textinactive),
                ContextCompat.getColor(All_photogs.this, R.color.textactive)
        );
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // added on selected listener on tabs
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
