package com.example.vitorgreati.presapp;

import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.vitorgreati.presapp.adapters.DashboardPageAdapter;

public class DashboardActivity extends AppCompatActivity {

    private DashboardPageAdapter pageAdapter;
    private ViewPager viewPager;

    private int[] tabIcons = {
            R.drawable.ic_tab_user,
            R.drawable.ic_pres_name,
            R.drawable.ic_tab_attends
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbarDash);
        toolbar.setTitle(R.string.dashboard_title);;
        setSupportActionBar(toolbar);

        pageAdapter = new DashboardPageAdapter(this, getSupportFragmentManager());

        viewPager = findViewById(R.id.dashViewPager);
        viewPager.setAdapter(pageAdapter);

        TabLayout tabLayout = findViewById(R.id.tabsDash);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabIcons.length; ++i) {
            tabLayout.getTabAt(i).setIcon(tabIcons[i]);
            tabLayout.getTabAt(i).getIcon().setColorFilter(ContextCompat.getColor(this, R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
        }

    }
}
