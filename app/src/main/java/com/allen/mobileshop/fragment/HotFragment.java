package com.allen.mobileshop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.mobileshop.R;
import com.allen.mobileshop.adapter.HotAdapter;
import com.allen.mobileshop.bean.Page;
import com.allen.mobileshop.bean.Wares;
import com.allen.mobileshop.common.Contants;
import com.allen.mobileshop.http.OkHttpHelper;
import com.allen.mobileshop.http.SimpleCallback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;


/**
 * Created by Allen on 15/12/27.
 */
public class HotFragment extends BaseFragment {
    private RecyclerView hot_recyclerView;
    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
    private HotAdapter adapter;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_hot, container, false);
        hot_recyclerView = (RecyclerView) view.findViewById(R.id.hot_recycler_view);

        return view;
    }

    @Override
    public void init() {
        requestHot();

    }

    private void requestHot() {
        okHttpHelper.get(Contants.API.WARES_HOT+"?curPage=1&pageSize=10", new SimpleCallback<Page<Wares>>(getActivity()) {

            @Override
            public void onSuccess(Response response, Page<Wares> waresPage) {
                initHotRV(waresPage);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    private void initHotRV(Page<Wares> waresPage) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        hot_recyclerView.setLayoutManager(layoutManager);
        adapter = new HotAdapter(waresPage,getActivity());
        hot_recyclerView.setAdapter(adapter);
    }
}
