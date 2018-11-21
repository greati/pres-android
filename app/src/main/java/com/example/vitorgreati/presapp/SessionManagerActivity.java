package com.example.vitorgreati.presapp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.vitorgreati.presapp.adapters.DashboardPageAdapter;
import com.example.vitorgreati.presapp.adapters.SessionManagerPageAdapter;
import com.example.vitorgreati.presapp.model.PresSession;

public class SessionManagerActivity extends AppCompatActivity {

    private SessionManagerPageAdapter pageAdapter;
    private ViewPager viewPager;

    private PresSession session;

    private int[] tabIcons = {
            R.drawable.ic_tab_user,
            R.drawable.ic_pres_name
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbarDash);
        toolbar.setTitle(R.string.dashboard_title);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent != null) {
            this.session = (PresSession) intent.getSerializableExtra("session");
        }

        pageAdapter = new SessionManagerPageAdapter(this, getSupportFragmentManager(), session);

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
