package org.sang.gank.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.sang.gank.R;
import org.sang.gank.app.DetailActivity;
import org.sang.gank.bean.ItemBean;

/**
 * Created by 王松 on 2016/9/16.
 */
public class RvAdapter extends RecyclerView.Adapter {
    private Context context;
    private LayoutInflater inflater;
    private ItemBean itemBean;

    public RvAdapter(Context context, ItemBean itemBean) {
        this.context = context;
        this.itemBean = itemBean;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        ItemBean.ResultsBean resultsBean = itemBean.getResults().get(position);
        viewHolder.desc.setText(resultsBean.getDesc());
        viewHolder.user.setText("用户:" + resultsBean.getWho());
        String publishedAt = resultsBean.getPublishedAt();
//        viewHolder.publishTime.setText("发布时间:" + publishedAt.substring(0, publishedAt.lastIndexOf(".")).replace("T", " "));
        viewHolder.category.setText("分类:" + resultsBean.getType());
//        viewHolder.summary.setText(Html.fromHtml(resultsBean.getReadability()).toString().replaceAll("\n", ""));
    }

    @Override
    public int getItemCount() {
        return itemBean.getResults().size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView desc, user, publishTime, category, summary;

        public MyViewHolder(View itemView) {
            super(itemView);
            desc = (TextView) itemView.findViewById(R.id.desc);
            user = (TextView) itemView.findViewById(R.id.user);
            publishTime = (TextView) itemView.findViewById(R.id.publish_time);
            category = (TextView) itemView.findViewById(R.id.category);
//            summary = (TextView) itemView.findViewById(R.id.summary);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("resultBean", itemBean.getResults().get(getLayoutPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
