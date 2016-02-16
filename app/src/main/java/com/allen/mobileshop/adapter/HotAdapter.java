package com.allen.mobileshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.mobileshop.R;
import com.allen.mobileshop.bean.Page;
import com.allen.mobileshop.bean.Wares;
import com.squareup.picasso.Picasso;

/**
 * Created by Allen on 2016/2/16.
 */
public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {
    private Page<Wares> waresPage;
    private Context mContext;

    public HotAdapter(Page<Wares> waresPage, Context mContext) {
        this.waresPage = waresPage;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hot_wares_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Wares wares = waresPage.getList().get(position);
        Picasso picasso = Picasso.with(mContext);
        picasso.setLoggingEnabled(true);//查看log日志
        picasso.setIndicatorsEnabled(true);
        picasso.load(wares.getImgUrl()).into(holder.img);

        holder.title.setText(wares.getName());
        holder.price.setText(wares.getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return waresPage.getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title;
        private TextView price;
        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_view);
            title = (TextView) itemView.findViewById(R.id.text_title);
            price = (TextView) itemView.findViewById(R.id.text_price);
        }
    }
}
