package com.example.vitorgreati.presapp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.vitorgreati.presapp.adapters.PresManagerPageAdapter;
import com.example.vitorgreati.presapp.model.Presentation;

public class PresManagerActivity extends AppCompatActivity {

    private PagerAdapter presManagerPageAdapter;
    private ViewPager viewPager;

    private Presentation pres;

    private int[] tabIcons = {
            R.drawable.ic_tab_user,
            R.drawable.ic_pres_name
    };

    public Presentation getPres() {
        return this.pres;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pres_manager);

        Intent intent = getIntent();
        if (intent != null) {
            this.pres = (Presentation) intent.getSerializableExtra("pres");
        }

        Toolbar toolbar = findViewById(R.id.toolbarPresManager);
        toolbar.setTitle(R.string.pres_manager_title);
        setSupportActionBar(toolbar);

        presManagerPageAdapter = new PresManagerPageAdapter(this, getSupportFragmentManager(), pres);

        viewPager = findViewById(R.id.vpPresManager);
        viewPager.setAdapter(presManagerPageAdapter);

        TabLayout tabLayout = findViewById(R.id.tabsPresManager);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabIcons.length; ++i) {
            tabLayout.getTabAt(i).setIcon(tabIcons[i]);
            tabLayout.getTabAt(i).getIcon().setColorFilter(ContextCompat.getColor(this, R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
        }
    }
}
