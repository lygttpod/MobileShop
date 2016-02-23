package com.allen.mobileshop.adapter;

import android.content.Context;

import com.allen.mobileshop.R;
import com.allen.mobileshop.bean.Wares;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Allen on 2016/2/17.
 */
public class WaresAdapter extends SimplerAdapter<Wares> {

    private Context mContent;

    public WaresAdapter(Context context, List<Wares> mDatas) {
        super(context, mDatas, R.layout.category_wares_item);
        this.mContent = context;
    }

    @Override
    public void bindData(BaseViewHolder holder, Wares item) {

        Picasso.with(mContext).load(item.getImgUrl()).into(holder.getImageView(R.id.img_view));
        holder.getTextView(R.id.text_title).setText(item.getName());
        holder.getTextView(R.id.text_price).setText(item.getPrice() + "");
    }
}
