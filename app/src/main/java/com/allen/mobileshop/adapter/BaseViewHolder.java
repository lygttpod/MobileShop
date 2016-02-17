package com.allen.mobileshop.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Allen on 2016/2/17.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private SparseArray<View> views;
    protected BaseAdapter.OnItemClickListener mOnItemClickListener;

    public BaseViewHolder(View itemView, BaseAdapter.OnItemClickListener listener) {
        super(itemView);
        this.mOnItemClickListener = listener;
        views = new SparseArray<>();

        itemView.setOnClickListener(this);
    }

    public TextView getTextView(int viewId) {
        return findView(viewId);
    }

    public ImageView getImageView(int viewId) {
        return findView(viewId);
    }

    public Button getButton(int viewId) {
        return findView(viewId);
    }

    public View getView(int viewId) {
        return findView(viewId);
    }

    /**
     * 实现FindviewById
     *
     * @param viewId
     * @param <T>
     * @return
     */
    protected <T extends View> T findView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    @Override
    public void onClick(View v) {
        mOnItemClickListener.onClick(v, getLayoutPosition());
    }
}
