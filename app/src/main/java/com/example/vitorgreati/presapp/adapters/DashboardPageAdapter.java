package com.example.vitorgreati.presapp.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.config.AppUtils;
import com.example.vitorgreati.presapp.fragments.DashAttendsFragment;
import com.example.vitorgreati.presapp.fragments.DashPresentationsFragment;
import com.example.vitorgreati.presapp.fragments.DashProfileFragment;

public class DashboardPageAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 3;

    private static final int FRAG_PROFILE = 0;
    private static final int FRAG_PRESENTATIONS = 1;
    private static final int FRAG_ATTENDS = 2;

    private Context ctx;

    public DashboardPageAdapter(Context ctx, FragmentManager fm) {
        super(fm);
        this.ctx = ctx;
    }

    @Override
    public Fragment getItem(int i) {
        switch(i) {
            case FRAG_PROFILE:
                return DashProfileFragment.newInstance(AppUtils.getLoggedUser(this.ctx));
            case FRAG_PRESENTATIONS:
                return DashPresentationsFragment.newInstance(AppUtils.getLoggedUser(this.ctx));
            case FRAG_ATTENDS:
                return DashAttendsFragment.newInstance(AppUtils.getLoggedUser(this.ctx));
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case FRAG_PROFILE:
                return ctx.getString(R.string.tab_profile_title);
            case FRAG_PRESENTATIONS:
                return ctx.getString(R.string.tab_presentations_title);
            case FRAG_ATTENDS:
                return ctx.getString(R.string.tab_attends_title);
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
