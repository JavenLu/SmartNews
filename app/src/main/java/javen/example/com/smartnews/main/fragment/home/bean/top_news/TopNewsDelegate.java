package javen.example.com.smartnews.main.fragment.home.bean.top_news;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import javen.example.com.smartnews.R;
import javen.example.com.smartnews.image.ImageHelper;
import javen.example.com.smartnews.main.delegate.AdapterDelegate;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.IDispalyNews;

/**
 * Created by Javen on 22/11/2017.
 */

public class TopNewsDelegate extends AdapterDelegate<List<IDispalyNews>> {
    private LayoutInflater inflater;
    private Context context;

    public TopNewsDelegate(Activity activity) {
        inflater = activity.getLayoutInflater();
        context = activity;
    }

    @Override
    protected boolean isForViewType(@NonNull List<IDispalyNews> items, int position) {
        return items.get(position) instanceof TopNewsBean;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.top_news_recycler_item_layout, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull List<IDispalyNews> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        TopNewsBean topNewsBean = (TopNewsBean) items.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;

        boolean isCheckHasAllPic = isCheckHasAllPic(topNewsBean);
        showLayout(viewHolder, isCheckHasAllPic);
        showContent(topNewsBean, viewHolder, isCheckHasAllPic);

    }

    private void showContent(TopNewsBean topNewsBean, ViewHolder viewHolder, boolean isCheckHasAllPic) {
        if (isCheckHasAllPic) {
            viewHolder.topNewsThreeLayoutTitleTextView.setText(topNewsBean.getTitle());
            viewHolder.topNewsThreeLayoutAuthorName.setText(topNewsBean.getAuthor_name());
            viewHolder.topNewsThreeLayoutPublishTimeFromNow.setText(topNewsBean.getDate());

            ImageHelper.setImageByGlide(context, topNewsBean.getThumbnail_pic_s(), viewHolder.topNewsThreeLayoutTitleImageLeft);
            ImageHelper.setImageByGlide(context, topNewsBean.getThumbnail_pic_s02(), viewHolder.topNewsThreeLayoutTitleImageMiddle);
            ImageHelper.setImageByGlide(context, topNewsBean.getThumbnail_pic_s03(), viewHolder.topNewsThreeLayoutTitleImageRight);
        } else {
            viewHolder.topNewsOneLayoutAuthorName.setText(topNewsBean.getAuthor_name());
            viewHolder.topNewsOneLayoutContentTextView.setText(topNewsBean.getTitle());
            viewHolder.topNewsOneLayoutPublishTimeFromNow.setText(topNewsBean.getDate());
            ImageHelper.setImageByGlide(context, topNewsBean.getThumbnail_pic_s(), viewHolder.topNewsOneLayoutImageView);
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

    private boolean isCheckHasAllPic(TopNewsBean topNewsBean) {
        return !TextUtils.isEmpty(topNewsBean.getThumbnail_pic_s()) && !TextUtils.isEmpty(topNewsBean.getThumbnail_pic_s02()) && !TextUtils.isEmpty(topNewsBean.getThumbnail_pic_s03());
    }

    /**
     * 这里由于公共云部分数据类型与图片字段有null，故暂时根据能够获取到的图片地址数量显示布局
     * 对于不同布局可以看成是不同类型，可以新建不同的Delegate,后期进行优化
     */
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