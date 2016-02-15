package com.allen.mobileshop.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.mobileshop.R;
import com.allen.mobileshop.adapter.HomeCampaignAdapter;
import com.allen.mobileshop.bean.Banner;
import com.allen.mobileshop.bean.HomeCampaign;
import com.allen.mobileshop.http.OkHttpHelper;
import com.allen.mobileshop.http.SimpleCallback;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 15/12/27.
 */
public class HomeFragment extends BaseFragment {
    private SliderLayout mSliderLayout;
    private RecyclerView mRecyclerView;
    private List<Banner> mBanners = new ArrayList<>();
    private List<HomeCampaign> mHomeCampaigns = new ArrayList<>();

    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, container, false);
        mSliderLayout = (SliderLayout) rootView.findViewById(R.id.slider_layout);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        return rootView;
    }

    @Override
    public void init() {
        requestImages();
        requestHome();
    }




    private void requestImages() {
        String url = "http://112.124.22.238:8081/course_api/banner/query?type=1";

        okHttpHelper.get(url, new SimpleCallback<List<Banner>>(getActivity()) {

            @Override
            public void onSuccess(Response response, List<Banner> banners) {
                mBanners = banners;
                initBanner();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }

    private void requestHome() {
        String url = "http://112.124.22.238:8081/course_api/campaign/recommend";
        okHttpHelper.get(url, new SimpleCallback<List<HomeCampaign>>(getActivity()) {

            @Override
            public void onSuccess(Response response, List<HomeCampaign> homeCampaigns) {
                mHomeCampaigns = homeCampaigns;
                initRecyclerView(mHomeCampaigns);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    private void initRecyclerView(List<HomeCampaign> homeCampaigns) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        HomeCampaignAdapter adapter = new HomeCampaignAdapter(homeCampaigns, getActivity());
        mRecyclerView.setAdapter(adapter);
    }

    private void initBanner() {
        for (Banner banner : mBanners) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView.description(banner.getName()).image(banner.getImgUrl());
            mSliderLayout.addSlider(textSliderView);
        }
    }

}
