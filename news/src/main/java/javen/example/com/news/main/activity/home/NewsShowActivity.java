package javen.example.com.news.main.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import javen.example.com.commonlibrary.CommonLibraryApplication;
import javen.example.com.commonlibrary.bean.news.NewsBean;
import javen.example.com.commonlibrary.bean.news.NewsBeanDao;
import javen.example.com.commonlibrary.custom_view.CustomToolBar;
import javen.example.com.commonlibrary.custom_view.decoration.DividerDecoration;
import javen.example.com.commonlibrary.glide.ImageHelper;
import javen.example.com.commonlibrary.utils.WindowUtil;
import javen.example.com.news.R;

/**
 * Created by Javen on 09/02/2018.
 */

public class NewsShowActivity extends AppCompatActivity {
    private String historyKey;
    private List<NewsBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ContentActivityTheme);
        setContentView(R.layout.news_show_activity_layout);
        init();
        initVIew();
        showDataToRecyclerView();
    }

    private void showDataToRecyclerView() {
        list = CommonLibraryApplication.daoSession.getNewsBeanDao().queryBuilder().where(NewsBeanDao.Properties.Title.like(historyKey)).list();
    }

    private void init() {
        historyKey = getIntent().getStringExtra("key");
    }

    private void initVIew() {
        WindowUtil.getInstance().setStatusBarTextAndIconDark(NewsShowActivity.this);
        initCustomToolBar();
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.news_show_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerDecoration(this, R.dimen.x1, R.dimen.x16, DividerDecoration.BOTTOM_LINE_TYPE));
        recyclerView.setAdapter(new Adapter());
    }

    private void initCustomToolBar() {
        CustomToolBar customToolBar = findViewById(R.id.custom_toolbar);
        customToolBar.setNavigationIcon(R.drawable.back_arrow);
        customToolBar.setToolbarType(CustomToolBar.TOOLBAR_NEWS_DETAIL);

        customToolBar.setNavigationOnClickListener(v -> finishCurrentActivity());
    }

    private void finishCurrentActivity() {
        finish();
        overridePendingTransition(0, R.anim.common_right_out);
    }

    private class Adapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(NewsShowActivity.this).inflate(R.layout.top_news_recycler_item_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            final NewsBean newsBean = list.get(position);
            ViewHolder viewHolder = (ViewHolder) holder;

            boolean isCheckHasAllPic = isCheckHasAllPic(newsBean);
            showLayout(viewHolder, isCheckHasAllPic);
            showContent(newsBean, viewHolder, isCheckHasAllPic);
            setItemClickListener(newsBean, viewHolder);
        }

        @Override
        public int getItemCount() {
            return list != null && list.size() > 0 ? list.size() : 0;
        }
    }

    private void setItemClickListener(final NewsBean newsBean, ViewHolder viewHolder) {
        viewHolder.topNewsOneLayout.setOnClickListener(v -> jumpToNewsDetailsActivity(newsBean));
        viewHolder.topNewsThreeLayout.setOnClickListener(v -> jumpToNewsDetailsActivity(newsBean));
    }

    private void jumpToNewsDetailsActivity(NewsBean newsBean) {
        Intent intent = new Intent(NewsShowActivity.this, NewsDetailsActivity.class);
        intent.putExtra("webUrl", newsBean.getUrl());
        startActivity(intent);
        overridePendingTransition(R.anim.common_right_in, R.anim.anim_stay);
    }

    private void showContent(NewsBean newsBean, ViewHolder viewHolder, boolean isCheckHasAllPic) {
        if (isCheckHasAllPic) {
            viewHolder.topNewsThreeLayoutTitleTextView.setText(newsBean.getTitle());
            viewHolder.topNewsThreeLayoutAuthorName.setText(newsBean.getAuthor_name());
            viewHolder.topNewsThreeLayoutPublishTimeFromNow.setText(newsBean.getDate());

            ImageHelper.setImageByGlide(NewsShowActivity.this, newsBean.getThumbnail_pic_s(), viewHolder.topNewsThreeLayoutTitleImageLeft);
            ImageHelper.setImageByGlide(NewsShowActivity.this, newsBean.getThumbnail_pic_s02(), viewHolder.topNewsThreeLayoutTitleImageMiddle);
            ImageHelper.setImageByGlide(NewsShowActivity.this, newsBean.getThumbnail_pic_s03(), viewHolder.topNewsThreeLayoutTitleImageRight);
        } else {
            viewHolder.topNewsOneLayoutAuthorName.setText(newsBean.getAuthor_name());
            viewHolder.topNewsOneLayoutContentTextView.setText(newsBean.getTitle());
            viewHolder.topNewsOneLayoutPublishTimeFromNow.setText(newsBean.getDate());
            ImageHelper.setImageByGlide(NewsShowActivity.this, newsBean.getThumbnail_pic_s(), viewHolder.topNewsOneLayoutImageView);
        }
    }

    private void showLayout(ViewHolder viewHolder, boolean isCheckHasAllPic) {
        if (isCheckHasAllPic) {
            viewHolder.topNewsThreeLayout.setVisibility(View.VISIBLE);
            viewHolder.topNewsOneLayout.setVisibility(View.GONE);
        } else {
            viewHolder.topNewsThreeLayout.setVisibility(View.GONE);
            viewHolder.topNewsOneLayout.setVisibility(View.VISIBLE);
        }
    }

    private boolean isCheckHasAllPic(NewsBean newsBean) {
        return !TextUtils.isEmpty(newsBean.getThumbnail_pic_s()) && !TextUtils.isEmpty(newsBean.getThumbnail_pic_s02()) && !TextUtils.isEmpty(newsBean.getThumbnail_pic_s03());
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout topNewsOneLayout, topNewsThreeLayout;
        TextView topNewsOneLayoutContentTextView, topNewsOneLayoutAuthorName, topNewsOneLayoutPublishTimeFromNow;
        ImageView topNewsOneLayoutImageView;
        TextView topNewsThreeLayoutTitleTextView, topNewsThreeLayoutAuthorName, topNewsThreeLayoutPublishTimeFromNow;
        ImageView topNewsThreeLayoutTitleImageLeft, topNewsThreeLayoutTitleImageMiddle, topNewsThreeLayoutTitleImageRight;

        public ViewHolder(View itemView) {
            super(itemView);
            onePicLayoutFindViewById(itemView);
            ThreePicLayoutFindViewById(itemView);
        }

        private void ThreePicLayoutFindViewById(View itemView) {
            topNewsThreeLayout = itemView.findViewById(R.id.top_news_three_layout);
            topNewsThreeLayoutTitleTextView = itemView.findViewById(R.id.title_text_view);
            topNewsThreeLayoutTitleImageLeft = itemView.findViewById(R.id.image_view_left);
            topNewsThreeLayoutTitleImageMiddle = itemView.findViewById(R.id.image_view_middle);
            topNewsThreeLayoutTitleImageRight = itemView.findViewById(R.id.image_view_right);
            topNewsThreeLayoutAuthorName = itemView.findViewById(R.id.author_name);
            topNewsThreeLayoutPublishTimeFromNow = itemView.findViewById(R.id.publish_time_from_now);
        }

        private void onePicLayoutFindViewById(View itemView) {
            topNewsOneLayout = itemView.findViewById(R.id.top_news_one_layout);
            topNewsOneLayoutContentTextView = itemView.findViewById(R.id.content_text_view);
            topNewsOneLayoutImageView = itemView.findViewById(R.id.image_view_one_pic_layout);
            topNewsOneLayoutAuthorName = itemView.findViewById(R.id.author_name_one_pic_layout);
            topNewsOneLayoutPublishTimeFromNow = itemView.findViewById(R.id.publish_time_from_now_one_pic_layout);
        }


    }


}
