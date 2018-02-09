package javen.example.com.news.main.router.logic;

import com.spinytech.macore.multiprocess.BaseApplicationLogic;
import com.spinytech.macore.router.LocalRouter;

import javen.example.com.commonlibrary.Constant;
import javen.example.com.news.main.router.provider.ProviderNews;

/**
 * Created by Javen on 08/02/2018.
 */

public class NewsApplicationLogic extends BaseApplicationLogic {
    @Override
    public void onCreate() {
        LocalRouter.getInstance(mApplication).registerProvider(Constant.NEWS_PROVIDER, new ProviderNews());
    }
}
