package com.allen.mobileshop.adapter;

import android.content.Context;

import com.allen.mobileshop.R;
import com.allen.mobileshop.bean.Category;

import java.util.List;

/**
 * Created by Allen on 2016/2/22.
 */
public class CategoryAdapter extends SimplerAdapter<Category> {

    public CategoryAdapter(Context context, List<Category> mDatas) {
        super(context, mDatas, R.layout.category_item_layout);
    }

    @Override
    public void bindData(BaseViewHolder holder, Category item) {
        holder.getTextView(R.id.category_title_tv).setText(item.getName());
    }
}
