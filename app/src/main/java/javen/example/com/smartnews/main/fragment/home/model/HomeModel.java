package javen.example.com.smartnews.main.fragment.home.model;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javen.example.com.smartnews.MyApplication;
import javen.example.com.smartnews.main.fragment.home.iinterface.IHomeModel;

/**
 * Created by Javen on 15/11/2017.
 */

public class HomeModel implements IHomeModel {

    @Override
    public List<Fragment> getHomeFragments() {
        List<Fragment> list = new ArrayList<>();
        HashMap<String, Fragment> typeHashMap = MyApplication.typeHashMap;

        for (Object o : typeHashMap.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            list.add((Fragment) entry.getValue());
        }

        return list;
    }
}
