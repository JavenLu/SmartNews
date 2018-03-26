package javen.example.com.smartnews;

import javen.example.com.commonlibrary.CommonLibraryApplication;
import javen.example.com.news.main.router.logic.NewsApplicationLogic;

/**
 * Created by Javen on 08/02/2018.
 */

public class CustomApplication extends CommonLibraryApplication {
    @Override
    public void initializeAllProcessRouter() {

    }

    @Override
    protected void initializeLogic() {
        registerApplicationLogic("javen.example.com.smartnews",999, NewsApplicationLogic.class);
    }

    @Override
    public boolean needMultipleProcess() {
        return false;
    }


}
