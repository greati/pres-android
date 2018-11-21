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
import com.example.vitorgreati.presapp.fragments.PresEditFragment;
import com.example.vitorgreati.presapp.fragments.PresSessionsManFragment;
import com.example.vitorgreati.presapp.model.Presentation;

public class PresManagerPageAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 2;

    private static final int FRAG_INFO = 0;
    private static final int FRAG_SESSIONS = 1;

    private Context ctx;

    private Presentation pres;

    public PresManagerPageAdapter(Context ctx, FragmentManager fm, Presentation pres) {
        super(fm);
        this.ctx = ctx;
        this.pres = pres;
    }

    @Override
    public Fragment getItem(int i) {
        switch(i) {
            case FRAG_INFO:
                return PresEditFragment.newInstance(pres);
            case FRAG_SESSIONS:
                return PresSessionsManFragment.newInstance(pres);
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case FRAG_INFO:
                return ctx.getString(R.string.tab_pres_info);
            case FRAG_SESSIONS:
                return ctx.getString(R.string.tab_pres_sessions);
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
