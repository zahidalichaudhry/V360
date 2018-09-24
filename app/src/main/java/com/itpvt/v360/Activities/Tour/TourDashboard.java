package com.itpvt.v360.Activities.Tour;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.itpvt.v360.Activities.Home;
import com.itpvt.v360.R;

public class TourDashboard extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    TextView click;
    TourDashboardPager pageradapter;
    String category, Phone, emailid;

    private ProgressDialog loading;

    private View navHeader;
    private TextView txtName, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tour_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // change toolbar background image and title texr color
//        toolbar.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.actionbar_bg));
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(),R.color.textactive));
//        toolbar.setNavigationIcon(R.drawable.lock);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        click=(TextView)findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TourDashboard.this,Home.class);
                startActivity(intent);
            }
        });

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);

        pageradapter = new TourDashboardPager(getSupportFragmentManager());
        viewPager.setAdapter(pageradapter);

        // initialized all the tabs
        final TabLayout.Tab home = tabLayout.newTab();
        final TabLayout.Tab gallery = tabLayout.newTab();
        final TabLayout.Tab events = tabLayout.newTab();

        // set tabs title
        home.setText("Home");
        gallery.setText("Gallery");
        events.setText("Book Now");

        // adding the tabs to tab layout
        tabLayout.addTab(home,0);
        tabLayout.addTab(gallery,1);
        tabLayout.addTab(events,2);

//         tabs text color
        tabLayout.setTabTextColors(
                ContextCompat.getColor(TourDashboard.this, R.color.textinactive),
                ContextCompat.getColor(TourDashboard.this, R.color.textactive)
        );
        // Added listener when the tabs changes
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
