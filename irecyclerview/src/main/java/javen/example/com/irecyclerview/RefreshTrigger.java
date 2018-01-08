package javen.example.com.irecyclerview;


public interface RefreshTrigger {

    void onStart(boolean automatic, int headerHeight, int finalHeight);

    void onMove(boolean finished, boolean automatic, int moved);

    void onRefresh();

    void onRelease();

    void onComplete();

    void onReset();

    void switchReleaseAndSwipeStatus(int status);
}
