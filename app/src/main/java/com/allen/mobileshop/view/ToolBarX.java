package com.allen.mobileshop.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.allen.mobileshop.R;

/**
 * Created by Allen on 2016/1/21.
 */
public class ToolBarX {

    private Toolbar mToolbar;
    private AppCompatActivity mActivity;
    private android.support.v7.app.ActionBar mActionBar;
    private RelativeLayout rlCustom;

    public ToolBarX(Toolbar mToolbar, final AppCompatActivity mActivity) {
        this.mToolbar = mToolbar;
        rlCustom = (RelativeLayout) mToolbar.findViewById(R.id.rlCustom);
        this.mActivity = mActivity;
        this.mActivity.setSupportActionBar(mToolbar);
        this.mActionBar = mActivity.getSupportActionBar();
        this.mActionBar.setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });

    }

    public ToolBarX setTitle(String text) {
        mActionBar.setTitle(text);
        return this;
    }

    public ToolBarX setSubtitle(String text) {
        mActionBar.setSubtitle(text);
        return this;
    }

    public ToolBarX setTitle(int resId) {
        mActionBar.setTitle(resId);
        return this;
    }

    public ToolBarX setSubtitle(int resId) {
        mActionBar.setSubtitle(resId);
        return this;
    }

    public ToolBarX setNavigationOnClickListener(View.OnClickListener listener) {
        mToolbar.setNavigationOnClickListener(listener);
        return this;
    }

    public ToolBarX setNavigationIcon(int resId) {
        mToolbar.setNavigationIcon(resId);
        return this;
    }

    public ToolBarX setDisplayHomeAsUpEnabled(boolean show) {
        mActionBar.setDisplayHomeAsUpEnabled(show);
        return this;
    }
    public ToolBarX setCustomView(View view){
        rlCustom.removeView(view);
        rlCustom.addView(view);
        return this;
    }
}
