package com.allen.mobileshop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.allen.mobileshop.R;
import com.allen.mobileshop.activity.HotDetailActivity;
import com.allen.mobileshop.adapter.BaseAdapter;
import com.allen.mobileshop.adapter.BaseViewHolder;
import com.allen.mobileshop.adapter.HotAdapter;
import com.allen.mobileshop.bean.Page;
import com.allen.mobileshop.bean.Wares;
import com.allen.mobileshop.common.Contants;
import com.allen.mobileshop.http.OkHttpHelper;
import com.allen.mobileshop.http.SimpleCallback;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Allen on 15/12/27.
 */
public class HotFragment extends BaseFragment {
    private RecyclerView hot_recyclerView;
    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();

    private List<Wares> wares = new ArrayList<>();
    private HotAdapter adapter;

    private MaterialRefreshLayout mRefreshLayout;

    private static int curPage = 1;
    private static int pageSize = 10;
    private static int totalPage = 1;


    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFREH = 1;
    private static final int STATE_MORE = 2;

    private int state = STATE_NORMAL;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_hot, container, false);
        hot_recyclerView = (RecyclerView) view.findViewById(R.id.hot_recycler_view);
        mRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        return view;
    }

    @Override
    public void init() {
        //requestHot(curPage, pageSize);
        initHotRV();
        initRefresh();
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

    private void requestHot(final int curgage, int pagesize) {
        okHttpHelper.get(Contants.API.WARES_HOT + "?curPage=" + curgage + "&pageSize=" + pagesize, new SimpleCallback<Page<Wares>>(getActivity()) {

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

    private void refreshData() {
        state = STATE_REFREH;
        curPage = 1;
        requestHot(curPage, pageSize);
    }

    private void loadMoreData() {
        state = STATE_MORE;
        curPage = ++curPage;
        requestHot(curPage, pageSize);
    }

    private void showData() {
        switch (state) {
            case STATE_NORMAL:
                initHotRV();
                break;
            case STATE_REFREH:
                adapter.clearData();
                adapter.addData(wares);
                hot_recyclerView.scrollToPosition(0);
                mRefreshLayout.finishRefresh();
                break;
            case STATE_MORE:
                adapter.addData(adapter.getDaras().size(), wares);
                hot_recyclerView.scrollToPosition(adapter.getDaras().size());
                mRefreshLayout.finishRefreshLoadMore();
                break;
        }
    }

    private void initHotRV() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        hot_recyclerView.setLayoutManager(layoutManager);
        adapter = new HotAdapter(getContext(), wares);
        adapter.setmOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getActivity(), HotDetailActivity.class);
                startActivity(intent);
            }
        });
        hot_recyclerView.setAdapter(adapter);
    }
}
