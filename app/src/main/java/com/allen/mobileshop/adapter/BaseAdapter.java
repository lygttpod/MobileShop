package com.allen.mobileshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 封装baseadapter
 * Created by Allen on 2016/2/17.
 */
public abstract class BaseAdapter<T, H extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder> {
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected int mLayoutResId;

    protected OnItemClickListener mOnItemClickListener;

    public BaseAdapter(Context context, List<T> mDatas, int layoutResId) {
        this.mContext = context;
        this.mDatas = mDatas;
        this.mLayoutResId = layoutResId;

        mInflater = LayoutInflater.from(context);
    }

    public void setmOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(mLayoutResId, null, false);
        return new BaseViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        T item = getItem(position);
        bindData(holder, item);
    }

    @Override
    public int getItemCount() {
        if (mDatas == null || mDatas.size() <= 0)
            return 0;
        return mDatas.size();
    }

    /**
     * 自定义geiItem方法   获取数据传递给viewholder
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        if (position >= mDatas.size()) {
            return null;
        } else {
            return mDatas.get(position);
        }
    }

    /**
     * viewholder绑定数据
     *
     * @param holder
     * @param item
     */
    public abstract void bindData(BaseViewHolder holder, T item);

    ////////////////////////基类提供一些常用方法/////////////////
    public List<T> getDaras() {
        return mDatas;
    }

    /**
     * 清空数据
     */
    public void clearData() {
        mDatas.clear();
        notifyItemRangeChanged(0, mDatas.size());
    }

    /**
     * 添加数据
     *
     * @param datas
     */
    public void addData(List<T> datas) {
        addData(0, datas);
    }

    /**
     * 添加更多数据
     *
     * @param position
     * @param datas
     */
    public void addData(int position, List<T> datas) {
        if (datas != null && datas.size() > 0) {
            mDatas.addAll(datas);
            notifyItemRangeChanged(position, mDatas.size());
        }
    }
}
