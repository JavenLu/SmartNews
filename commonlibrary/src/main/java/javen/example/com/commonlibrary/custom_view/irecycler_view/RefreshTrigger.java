package javen.example.com.commonlibrary.custom_view.irecycler_view;


public interface RefreshTrigger {

    void onStart(boolean automatic, int headerHeight, int finalHeight);

    void onMove(boolean finished, boolean automatic, int moved);

    void onRefresh();

    void onRelease();

    void onComplete();

    void onReset();

    void switchReleaseAndSwipeStatus(int status);
}
