package javen.example.com.smartnews.main.fragment.a_little_video.presenter;

import javen.example.com.smartnews.main.fragment.BaseFragment;
import javen.example.com.smartnews.main.fragment.BaseFragmentPresenter;
import javen.example.com.smartnews.main.fragment.a_little_video.ALittleVideoFragment;
import javen.example.com.smartnews.main.fragment.a_little_video.iinterface.IALittleVideoFragment;
import javen.example.com.smartnews.main.fragment.a_little_video.iinterface.IALittleVideoModel;
import javen.example.com.smartnews.main.fragment.a_little_video.iinterface.IALittleVideoPresenter;
import javen.example.com.smartnews.main.fragment.a_little_video.model.ALittleVideoModel;

/**
 * Created by Javen on 14/11/2017.
 */

public class ALittleVideoFragmentPresenter extends BaseFragmentPresenter<ALittleVideoFragment> implements IALittleVideoPresenter {
    private IALittleVideoFragment iaLittleVideoFragment;
    private IALittleVideoModel iaLittleVideoModel;

    public ALittleVideoFragmentPresenter(IALittleVideoFragment iaLittleVideoFragment) {
        this.iaLittleVideoFragment = iaLittleVideoFragment;
        this.iaLittleVideoModel = new ALittleVideoModel();
    }

}
