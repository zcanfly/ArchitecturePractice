package com.lijiankun24.architecturepractice.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lijiankun24.architecturepractice.R;
import com.lijiankun24.architecturepractice.data.remote.model.ZhihuStory;
import com.lijiankun24.architecturepractice.ui.activity.ZhihuActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * ZhihuListAdapter.java
 * <p>
 * Created by lijiankun on 17/7/30.
 */

public class ZhihuListAdapter extends RecyclerView.Adapter<ZhihuListAdapter.ZhihuViewHolder> {

    private Context mContext = null;

    private List<ZhihuStory> mStoryList = null;

    public ZhihuListAdapter(Context context) {
        mStoryList = new ArrayList<>();
        mContext = context;
    }

    @Override
    public ZhihuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ZhihuViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_zhihu_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ZhihuViewHolder holder, int position) {
        ZhihuStory zhihuStory = mStoryList.get(position);
        holder.getTVZhihuTitle().setText(zhihuStory.getTitle());
        holder.getTVZhihuTime().setText(zhihuStory.getGa_prefix());
        holder.getLLZhihu().setOnClickListener(new ItemClickListener(zhihuStory.getTitle(), zhihuStory.getId()));
        Glide.with(mContext)
                .load(zhihuStory.getImages()[0])
                .centerCrop()
                .into(holder.getIVZhihu());
    }

    @Override
    public int getItemCount() {
        return mStoryList.size();
    }

    public void setStoryList(List<ZhihuStory> storyList) {
        if (storyList == null || storyList.size() == 0) {
            return;
        }
        mStoryList.addAll(storyList);
        notifyDataSetChanged();
    }

    public void clearStoryList() {
        mStoryList.clear();
        notifyDataSetChanged();
    }

    private class ItemClickListener implements View.OnClickListener {

        private String mZhihuTitle = null;

        private String mZhihuId = null;

        public ItemClickListener(String zhihuTitle, String zhihuId) {
            mZhihuTitle = zhihuTitle;
            mZhihuId = zhihuId;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, ZhihuActivity.class);
            intent.putExtra(ZhihuActivity.ZHIHU_ID, mZhihuId);
            intent.putExtra(ZhihuActivity.ZHIHU_TITLE, mZhihuTitle);
            mContext.startActivity(intent);
        }
    }

    class ZhihuViewHolder extends RecyclerView.ViewHolder {

        private View mLLZhihu;
        private TextView mTVZhihuTitle;
        private TextView mTVZhihuTime;
        private ImageView mIVZhihu;

        private ZhihuViewHolder(View itemView) {
            super(itemView);
            mLLZhihu = itemView.findViewById(R.id.ll_zhihu);
            mTVZhihuTitle = itemView.findViewById(R.id.tv_zhihu_title);
            mTVZhihuTime = itemView.findViewById(R.id.tv_zhihu_time);
            mIVZhihu = itemView.findViewById(R.id.iv_zhihu);
        }

        private View getLLZhihu() {
            return mLLZhihu;
        }

        private TextView getTVZhihuTitle() {
            return mTVZhihuTitle;
        }

        private TextView getTVZhihuTime() {
            return mTVZhihuTime;
        }

        private ImageView getIVZhihu() {
            return mIVZhihu;
        }
    }
}