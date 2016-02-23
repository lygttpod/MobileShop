package com.allen.mobileshop.fragment;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.allen.mobileshop.R;
import com.allen.mobileshop.utils.ToastUtils;

import java.util.List;

/**
 * Created by Allen on 2016/2/23.
 */
public class MyPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener{
    private Context context;
    public MyPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.mipmap.help_pic);
        ((ViewPager) container).addView(imageView, position);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("onPageScrolled","position="+position+"positionOffset="+positionOffset+"positionOffsetPixels="+positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("onPageSelected","onPageSelected="+position);
        ToastUtils.showShort(context,"onPageSelected="+position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
