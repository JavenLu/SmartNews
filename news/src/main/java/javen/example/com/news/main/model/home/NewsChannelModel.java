package javen.example.com.news.main.model.home;

import android.annotation.SuppressLint;


import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import javen.example.com.commonlibrary.CommonLibraryApplication;
import javen.example.com.commonlibrary.bean.news.NewsChannelBean;
import javen.example.com.commonlibrary.bean.news.NewsChannelBeanDao;
import javen.example.com.commonlibrary.utils.RxJavaTransformerUtil;
import javen.example.com.commonlibrary.utils.SharedPreferencesUtil;
import javen.example.com.news.R;
import javen.example.com.news.db.DBConstant;
import javen.example.com.news.main.iinterface.RequestCallBack;
import javen.example.com.news.main.iinterface.home.INewsChannelModel;


/**
 * Created by Javen on 30/11/2017.
 */

public class NewsChannelModel implements INewsChannelModel<NewsChannelBean> {
    private ExecutorService mSingleThreadPool;

    @Override
    public void lodeNewsChannels(RequestCallBack<Map<Integer, List<NewsChannelBean>>> callback) {

        Observable.create((ObservableOnSubscribe<Map<Integer, List<NewsChannelBean>>>) emitter -> {
            initNewsChannelDB();
            Map<Integer, List<NewsChannelBean>> map = getAllNewsChannelDataReturnMap();

            emitter.onNext(map);
            emitter.onComplete();
        }).compose(RxJavaTransformerUtil.applyDefaultSchedulers())
                .subscribe(new Observer<Map<Integer, List<NewsChannelBean>>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Map<Integer, List<NewsChannelBean>> integerListMap) {
                        callback.success(integerListMap);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void initNewsChannelDB() {
        if (!SharedPreferencesUtil.getPreferenceBoolean(SharedPreferencesUtil.INIT_CHANNEL_DB, false)) {
            List<String> channelName = Arrays.asList(CommonLibraryApplication.getContext().getResources()
                    .getStringArray(R.array.news_channel_name));
            List<String> channelId = Arrays.asList(CommonLibraryApplication.getContext().getResources()
                    .getStringArray(R.array.news_channel_id));

            for (int i = 0; i < channelName.size(); i++) {
                NewsChannelBean newsChannelBean = new NewsChannelBean();
                newsChannelBean.setNewsChannelName(channelName.get(i));
                newsChannelBean.setNewsChannelId(channelId.get(i));
                newsChannelBean.setNewsChannelType(DBConstant.getType(channelId.get(i)));
                newsChannelBean.setNewsChannelSelect(i <= 3);
                newsChannelBean.setNewsChannelIndex(i);
                newsChannelBean.setNewsChannelFixed(i == 0);
                insertSingleObject(newsChannelBean);
            }

            SharedPreferencesUtil.savePreferenceBoolean(SharedPreferencesUtil.INIT_CHANNEL_DB, true);
        }
    }

    @Override
    public void insertSingleObject(NewsChannelBean object) {
        CommonLibraryApplication.daoSession.getNewsChannelBeanDao().insertOrReplace(object);
    }

    @Override
    public void upDateSingleObject(NewsChannelBean object) {
        CommonLibraryApplication.daoSession.getNewsChannelBeanDao().update(object);
    }

    @Override
    public List<NewsChannelBean> queryAllObjectByNewsChannelSelect() {
        QueryBuilder<NewsChannelBean> newsChannelBeanQueryBuilder = CommonLibraryApplication.daoSession.getNewsChannelBeanDao().queryBuilder();
        newsChannelBeanQueryBuilder.where(NewsChannelBeanDao.Properties.NewsChannelSelect.eq(true))
                .orderAsc(NewsChannelBeanDao.Properties.NewsChannelIndex).build();

        return newsChannelBeanQueryBuilder.list();
    }

    @Override
    public List<NewsChannelBean> queryAllObjectByNewsChannelUnSelect() {
        QueryBuilder<NewsChannelBean> newsChannelBeanQueryBuilder = CommonLibraryApplication.daoSession.getNewsChannelBeanDao().queryBuilder();
        newsChannelBeanQueryBuilder.where(NewsChannelBeanDao.Properties.NewsChannelSelect.eq(false))
                .orderAsc(NewsChannelBeanDao.Properties.NewsChannelIndex).build();

        return newsChannelBeanQueryBuilder.list();
    }

    @Override
    public Map<Integer, List<NewsChannelBean>> getAllNewsChannelDataReturnMap() {
        @SuppressLint("UseSparseArrays")
        Map<Integer, List<NewsChannelBean>> dataMap = new HashMap<>();

        List<NewsChannelBean> selectNewsChannelBeanList = queryAllObjectByNewsChannelSelect();
        List<NewsChannelBean> unSelectNewsChannelBeanList = queryAllObjectByNewsChannelUnSelect();
        dataMap.put(DBConstant.NEWS_CHANNEL_MINE, selectNewsChannelBeanList);
        dataMap.put(DBConstant.NEWS_CHANNEL_MORE, unSelectNewsChannelBeanList);

        return dataMap;
    }

    @Override
    public int getAllNewsChannelDataSize() {
        return CommonLibraryApplication.daoSession.getNewsChannelBeanDao().loadAll().size();
    }

    @Override
    public List<NewsChannelBean> loadNewsChannelsIndexGt(int channelIndex) {

        QueryBuilder<NewsChannelBean> newsChannelBeanQueryBuilder = CommonLibraryApplication.daoSession.getNewsChannelBeanDao().queryBuilder();
        newsChannelBeanQueryBuilder.where(NewsChannelBeanDao.Properties.NewsChannelIndex.gt(channelIndex)).build();

        return newsChannelBeanQueryBuilder.list();
    }

    public void upDateDBWhenOnItemClick(NewsChannelBean newsChannelBean, boolean isChannelMine) {
        if (mSingleThreadPool == null) {
            mSingleThreadPool = Executors.newSingleThreadExecutor();
        }

        mSingleThreadPool.execute(() -> {
            int channelIndex = newsChannelBean.getNewsChannelIndex();
            List<NewsChannelBean> newsChannelBeanList;

            if (isChannelMine) {
                newsChannelBeanList = loadNewsChannelsIndexGt(channelIndex);
                changeTheLocationOfTheSurroundingElements(newsChannelBeanList, false);
                int allNewsChannelDataSize = getAllNewsChannelDataSize();
                updateClickObject(newsChannelBean, allNewsChannelDataSize, false);

            } else {
                newsChannelBeanList = loadNewsChannelsLtAndUnSelect(channelIndex);
                changeTheLocationOfTheSurroundingElements(newsChannelBeanList, true);
                int selectedNewsDataSize = (int) getSelectedNewsDataSize();
                updateClickObject(newsChannelBean, selectedNewsDataSize, true);
            }
        });


    }

    private void updateClickObject(NewsChannelBean newsChannelBean, int toPosition, boolean isSelect) {
        newsChannelBean.setNewsChannelSelect(isSelect);
        newsChannelBean.setNewsChannelIndex(toPosition);
        upDateSingleObject(newsChannelBean);
    }

    @Override
    public List<NewsChannelBean> loadNewsChannelsLtAndUnSelect(int channelIndex) {
        QueryBuilder<NewsChannelBean> newsChannelBeanQueryBuilder = CommonLibraryApplication.daoSession.getNewsChannelBeanDao().queryBuilder();
        newsChannelBeanQueryBuilder.where(NewsChannelBeanDao.Properties.NewsChannelIndex.lt(channelIndex), NewsChannelBeanDao.Properties.NewsChannelSelect.eq(false))
                .build();
        return newsChannelBeanQueryBuilder.list();
    }

    @Override
    public long getSelectedNewsDataSize() {

        QueryBuilder<NewsChannelBean> newsChannelBeanQueryBuilder = CommonLibraryApplication.daoSession.getNewsChannelBeanDao().queryBuilder();

        return newsChannelBeanQueryBuilder.where(NewsChannelBeanDao.Properties.NewsChannelSelect.eq(true)).buildCount().count();
    }

    @Override
    public void upDataDBWhenDragFinished(int fromPosition, int toPosition) {
        updateDragDB(fromPosition, toPosition);
    }

    private void updateDragDB(int fromPosition, int toPosition) {
        createThreadPool();
        mSingleThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                NewsChannelBean fromNewsChannelBean = getNewsChannelSingleObjectByIndex(fromPosition);
                NewsChannelBean toNewsChannelBean = getNewsChannelSingleObjectByIndex(toPosition);

                if (isCheckFixed(fromNewsChannelBean, toNewsChannelBean)) return;

                if (isCheckNeighbor()) {
                    swapNeighborAndUpdate(fromNewsChannelBean, toNewsChannelBean);
                } else if (fromPosition - toPosition > 0) {
                    swapFromRightToLeftNotNeighborAndUpdate(fromNewsChannelBean);
                } else if (fromPosition - toPosition < 0) {
                    swapFromLeftToRightNotNeighborAndUpdate(fromNewsChannelBean);
                }

                CommonLibraryApplication.isChannelChange = true;

            }

            private void swapFromLeftToRightNotNeighborAndUpdate(NewsChannelBean fromNewsChannelBean) {
                List<NewsChannelBean> list = queryAllObjectByBetween(fromPosition + 1, toPosition);
                changeTheLocationOfTheSurroundingElements(list, false);
                fromNewsChannelBean.setNewsChannelIndex(toPosition);
                upDateSingleObject(fromNewsChannelBean);
            }

            private void swapFromRightToLeftNotNeighborAndUpdate(NewsChannelBean fromNewsChannelBean) {
                List<NewsChannelBean> list = queryAllObjectByBetween(toPosition, fromPosition - 1);
                changeTheLocationOfTheSurroundingElements(list, true);
                fromNewsChannelBean.setNewsChannelIndex(toPosition);
                upDateSingleObject(fromNewsChannelBean);
            }

            private void swapNeighborAndUpdate(NewsChannelBean fromNewsChannelBean, NewsChannelBean toNewsChannelBean) {
                fromNewsChannelBean.setNewsChannelIndex(toPosition);
                toNewsChannelBean.setNewsChannelIndex(fromPosition);

                upDateSingleObject(fromNewsChannelBean);
                upDateSingleObject(toNewsChannelBean);
            }

            private boolean isCheckNeighbor() {
                return Math.abs(fromPosition - toPosition) == 1;
            }

            private boolean isCheckFixed(NewsChannelBean fromNewsChannelBean, NewsChannelBean toNewsChannelBean) {
                return fromNewsChannelBean.getNewsChannelFixed() || toNewsChannelBean.getNewsChannelFixed();
            }
        });
    }

    @Override
    public NewsChannelBean getNewsChannelSingleObjectByIndex(int position) {
        QueryBuilder<NewsChannelBean> newsChannelBeanQueryBuilder = CommonLibraryApplication.daoSession.getNewsChannelBeanDao().queryBuilder();
        return newsChannelBeanQueryBuilder.where(NewsChannelBeanDao.Properties.NewsChannelIndex.eq(position)).build().unique();
    }

    @Override
    public List<NewsChannelBean> queryAllObjectByBetween(int from, int to) {
        QueryBuilder<NewsChannelBean> newsChannelBeanQueryBuilder = CommonLibraryApplication.daoSession.getNewsChannelBeanDao().queryBuilder();
        newsChannelBeanQueryBuilder.where(NewsChannelBeanDao.Properties.NewsChannelIndex.between(from, to)).build();
        return newsChannelBeanQueryBuilder.list();
    }

    private void changeTheLocationOfTheSurroundingElements(List<NewsChannelBean> newsChannelBeanList, boolean isIncrease) {
        int index;

        for (NewsChannelBean channelBean : newsChannelBeanList) {

            if (isIncrease) {
                index = channelBean.getNewsChannelIndex() + 1;
            } else {
                index = channelBean.getNewsChannelIndex() - 1;
            }

            channelBean.setNewsChannelIndex(index);
            upDateSingleObject(channelBean);
        }

    }

    private void createThreadPool() {
        if (mSingleThreadPool == null) {
            mSingleThreadPool = Executors.newSingleThreadExecutor();
        }
    }


}
