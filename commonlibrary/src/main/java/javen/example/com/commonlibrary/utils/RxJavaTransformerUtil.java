package javen.example.com.commonlibrary.utils;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Javen on 01/12/2017.
 */

public class RxJavaTransformerUtil {

    private static final ObservableTransformer schedulersTransformer =
            observable -> observable.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

    @SuppressWarnings("unchecked")
   public static <T> ObservableTransformer<T, T> applyDefaultSchedulers() {
        return (ObservableTransformer<T, T>) schedulersTransformer;
    }
}




