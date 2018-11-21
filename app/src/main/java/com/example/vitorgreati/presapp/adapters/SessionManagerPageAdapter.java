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
import com.example.vitorgreati.presapp.fragments.SessionEditFragment;
import com.example.vitorgreati.presapp.fragments.SessionQuestionsFragment;
import com.example.vitorgreati.presapp.model.PresSession;

public class SessionManagerPageAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 2;

    private static final int FRAG_INFO = 0;
    private static final int FRAG_QUESTIONS = 1;

    private Context ctx;

    private PresSession session;

    public SessionManagerPageAdapter(Context ctx, FragmentManager fm, PresSession session) {
        super(fm);
        this.ctx = ctx;
        this.session = session;
    }

    @Override
    public Fragment getItem(int i) {
        switch(i) {
            case FRAG_INFO:
                return SessionEditFragment.newInstance(session);
            case FRAG_QUESTIONS:
                return SessionQuestionsFragment.newInstance(session);
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case FRAG_INFO:
                return ctx.getString(R.string.tab_session_info);
            case FRAG_QUESTIONS:
                return ctx.getString(R.string.tab_session_questions);
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
