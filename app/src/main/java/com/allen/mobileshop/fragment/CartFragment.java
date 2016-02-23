package com.allen.mobileshop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.allen.mobileshop.R;


/**
 * Created by Allen on 15/12/27.
 */
public class CartFragment extends Fragment {
    ViewPager viewPager;
    RelativeLayout viewPagerContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_cart, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPagerContainer = (RelativeLayout) view.findViewById(R.id.relativelayout);
//        viewPagerContainer.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // dispatch the events to the ViewPager, to solve the problem that we can swipe only the middle view.
//                return viewPager.dispatchTouchEvent(event);
//            }
//        });
        initViewPager();
        return view;
    }

    private void initViewPager() {
        viewPager.setAdapter(new MyPagerAdapter(getContext()));
        viewPager.setOffscreenPageLimit(8);
        viewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.page_margin));

    }
}
