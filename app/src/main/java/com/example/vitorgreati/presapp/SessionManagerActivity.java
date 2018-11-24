package com.example.vitorgreati.presapp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vitorgreati.presapp.adapters.DashboardPageAdapter;
import com.example.vitorgreati.presapp.adapters.SessionManagerPageAdapter;
import com.example.vitorgreati.presapp.dialogs.StartSessionDialog;
import com.example.vitorgreati.presapp.model.PresSession;

public class SessionManagerActivity extends AppCompatActivity implements StartSessionDialog.OnSessionStartListener {

    private SessionManagerPageAdapter pageAdapter;
    private ViewPager viewPager;

    private PresSession session;

    private int[] tabIcons = {
            R.drawable.ic_tab_user,
            R.drawable.ic_pres_name
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Open session").setIcon(android.R.drawable.ic_media_play)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                StartSessionDialog dialog = new StartSessionDialog();
                dialog.show(getSupportFragmentManager(), "OpenSession");

                return true;
            }
        });

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbarDash);
        toolbar.setTitle(R.string.session_dashboard);
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

    @Override
    public void onStartSession() {
        Intent i = new Intent(this, ActiveSessionPresenterActivity.class);
        startActivity(i);
    }
}
