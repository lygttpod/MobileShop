package com.allen.mobileshop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.allen.mobileshop.R;
import com.allen.mobileshop.activity.HotDetailActivity;
import com.allen.mobileshop.adapter.BaseAdapter;
import com.allen.mobileshop.adapter.CategoryAdapter;
import com.allen.mobileshop.adapter.HotAdapter;
import com.allen.mobileshop.adapter.WaresAdapter;
import com.allen.mobileshop.bean.Banner;
import com.allen.mobileshop.bean.Category;
import com.allen.mobileshop.bean.Page;
import com.allen.mobileshop.bean.Wares;
import com.allen.mobileshop.common.Contants;
import com.allen.mobileshop.http.OkHttpHelper;
import com.allen.mobileshop.http.SimpleCallback;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Allen on 15/12/27.
 */
public class CategoryFragment extends Fragment {
    private RecyclerView recyclerView_category;
    private RecyclerView recyclerView_wares;
    private SliderLayout sliderLayout;
    private MaterialRefreshLayout mRefreshLayout;

    private CategoryAdapter categoryAdapter;
    private WaresAdapter waresAdapter;

    private List<Category> lists = new ArrayList<>();
    private List<Banner> mBanners = new ArrayList<>();
    private List<Wares> wares = new ArrayList<>();

    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();

    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFREH = 1;
    private static final int STATE_MORE = 2;

    private int state = STATE_NORMAL;

    private static int curPage = 1;
    private static int pageSize = 10;
    private static int totalPage = 1;
    private static int categoryId = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_category, container, false);
        recyclerView_category = (RecyclerView) view.findViewById(R.id.recyclerview_category);
        sliderLayout = (SliderLayout) view.findViewById(R.id.slider_layout);
        recyclerView_wares = (RecyclerView) view.findViewById(R.id.recyclerview_wares);
        mRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh_layout);
        requestCategory();
        requestImages();
        initWares();
        initRefresh();
        return view;
    }

    private void requestWares(int curgage, int pagesize, int categoryId) {
        okHttpHelper.get(Contants.API.WARES_LIST + "?curPage=" + curgage + "&pageSize=" + pagesize + "&categoryId=" + categoryId, new SimpleCallback<Page<Wares>>(getContext()) {

            @Override
            public void onSuccess(Response response, Page<Wares> waresPage) {
                wares = waresPage.getList();
                curPage = waresPage.getCurrentPage();
                totalPage = waresPage.getTotalPage();
                showData();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    private void initCategory() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView_category.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(getContext(), lists);
        categoryAdapter.setmOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                categoryId = lists.get(position).getId();
                refreshData();
            }
        });
        recyclerView_category.setAdapter(categoryAdapter);
    }

    private void requestCategory() {
        okHttpHelper.get(Contants.API.CATEGORY_LIST, new SimpleCallback<List<Category>>(getContext()) {

            @Override
            public void onSuccess(Response response, List<Category> categories) {
                lists = categories;
                state = STATE_NORMAL;
                showData();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    private void initRefresh() {
        mRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                refreshData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);

                if (curPage > totalPage) {
                    Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                    mRefreshLayout.finishRefreshLoadMore();
                } else {
                    loadMoreData();
                }
            }
        });
        mRefreshLayout.autoRefresh();

    }

    private void initWares() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView_wares.setLayoutManager(layoutManager);
        waresAdapter = new WaresAdapter(getContext(), wares);
        waresAdapter.setmOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getActivity(), HotDetailActivity.class);
                startActivity(intent);
            }
        });
        recyclerView_wares.setAdapter(waresAdapter);
    }

    private void refreshData() {
        state = STATE_REFREH;
        curPage = 1;
        requestWares(curPage, pageSize, categoryId);
    }

    private void loadMoreData() {
        state = STATE_MORE;
        curPage = ++curPage;
        requestWares(curPage, pageSize, categoryId);
    }

    private void showData() {
        switch (state) {
            case STATE_NORMAL:
                initCategory();
                break;
            case STATE_REFREH:
                waresAdapter.clearData();
                waresAdapter.addData(wares);
                recyclerView_wares.scrollToPosition(0);
                mRefreshLayout.finishRefresh();
                break;
            case STATE_MORE:
                waresAdapter.addData(waresAdapter.getDaras().size(), wares);
                recyclerView_wares.scrollToPosition(waresAdapter.getDaras().size());
                mRefreshLayout.finishRefreshLoadMore();
                break;
        }
    }

    private void requestImages() {

        okHttpHelper.get(Contants.API.BANNER + "?type=1", new SimpleCallback<List<Banner>>(getActivity()) {

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

    private void initBanner() {
        for (Banner banner : mBanners) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView.description(banner.getName()).image(banner.getImgUrl());
            sliderLayout.addSlider(textSliderView);
        }
    }


}
