package javen.example.com.smartnews.main.fragment.home.model.top_news;

import java.util.List;

import javen.example.com.smartnews.MyApplication;
import javen.example.com.smartnews.main.fragment.home.bean.top_news.TopNewsBean;
import javen.example.com.smartnews.main.fragment.home.bean.top_news.TopNewsBeanDao;
import javen.example.com.smartnews.main.fragment.home.iinterface.top_news.ITopNewsModel;

/**
 * Created by Javen on 17/11/2017.
 */

public class TopNewsModel implements ITopNewsModel<TopNewsBean> {

    @Override
    public void insertSingleObject(TopNewsBean object) {
        MyApplication.daoSession.getTopNewsBeanDao().insert(object);
    }

    @Override
    public void deleteSingleObject(TopNewsBean object) {
        MyApplication.daoSession.getTopNewsBeanDao().delete(object);
    }

    @Override
    public void updateSingleObject(TopNewsBean object) {
        MyApplication.daoSession.getTopNewsBeanDao().update(object);
    }

    @Override
    public List<TopNewsBean> queryAllObject() {
        return MyApplication.daoSession.getTopNewsBeanDao().loadAll();
    }

    @Override
    public List<TopNewsBean> queryAllObjectByType(String type) {
        return MyApplication.daoSession.getTopNewsBeanDao().queryBuilder().where(TopNewsBeanDao.Properties.Type.eq(type)).list();
    }
}
