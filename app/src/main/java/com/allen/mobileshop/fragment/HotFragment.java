package com.allen.mobileshop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.mobileshop.R;
import com.allen.mobileshop.http.OkHttpHelper;
import com.squareup.okhttp.OkHttpClient;


/**
 * Created by Allen on 15/12/27.
 */
public class HotFragment extends BaseFragment {
    private RecyclerView hot_recyclerView;
    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_hot,container,false);
        hot_recyclerView = (RecyclerView) view.findViewById(R.id.hot_recycler_view);

        return view;
    }
    @Override
    public void init() {
        requestHot();

    }

    private void requestHot() {
    }
}
