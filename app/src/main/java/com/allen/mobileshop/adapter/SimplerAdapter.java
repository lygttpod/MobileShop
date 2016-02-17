package com.allen.mobileshop.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by Allen on 2016/2/17.
 */
public abstract class SimplerAdapter<T> extends BaseAdapter<T, BaseViewHolder> {

    public SimplerAdapter(Context context, List<T> mDatas, int layoutResId) {
        super(context, mDatas, layoutResId);
    }

}
