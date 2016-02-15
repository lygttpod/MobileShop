package com.allen.mobileshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.mobileshop.R;
import com.allen.mobileshop.bean.HomeCampaign;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Allen on 2016/2/2.
 */
public class HomeCampaignAdapter extends RecyclerView.Adapter<HomeCampaignAdapter.ViewHolder> {
    private List<HomeCampaign> homeCampaigns;
    private Context mContext;

    public HomeCampaignAdapter(List<HomeCampaign> homeCampaigns, Context mContext) {
        this.homeCampaigns = homeCampaigns;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_item_cardview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(homeCampaigns.get(position).getTitle());
        HomeCampaign homeCampaign = homeCampaigns.get(position);
        Picasso picasso = Picasso.with(mContext);
        picasso.setLoggingEnabled(true);//查看log日志
        picasso.setIndicatorsEnabled(true);
        picasso.load(homeCampaign.getCpTwo().getImgUrl()).into(holder.pic_left_up);
        picasso.load(homeCampaign.getCpThree().getImgUrl()).into(holder.pic_left_down);
        picasso.load(homeCampaign.getCpOne().getImgUrl()).into(holder.pic_right);
//        Picasso.with(mContext).setLoggingEnabled(true).setIndicatorsEnabled(true).load(homeCampaign.getCpTwo().getImgUrl()).into(holder.pic_left_up);
//        Picasso.with(mContext).load(homeCampaign.getCpThree().getImgUrl()).into(holder.pic_left_down);
//        Picasso.with(mContext).load(homeCampaign.getCpOne().getImgUrl()).into(holder.pic_right);

    }

    @Override
    public int getItemCount() {
        return homeCampaigns.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView pic_left_up;
        private ImageView pic_left_down;
        private ImageView pic_right;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.home_item_title);
            pic_left_up = (ImageView) itemView.findViewById(R.id.home_item_pic_left_up);
            pic_left_down = (ImageView) itemView.findViewById(R.id.home_item_pic_left_down);
            pic_right = (ImageView) itemView.findViewById(R.id.home_item_pic_right);

        }
    }
}
