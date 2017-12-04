package javen.example.com.smartnews.main.iinterface;

/**
 * Created by Javen on 30/11/2017.
 */

public interface RequestCallBack<T> {

    void success(T data);

    void onError(String errorMsg);
}
