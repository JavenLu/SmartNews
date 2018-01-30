package javen.example.com.smartnews.main.activity.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import javen.example.com.smartnews.R;
import javen.example.com.smartnews.custom_view.CustomToolBar;
import javen.example.com.smartnews.main.fragment.home.fragments.WebViewErrorFragment;
import javen.example.com.smartnews.utils.DialogUtil;
import javen.example.com.smartnews.utils.NetUtil;
import javen.example.com.smartnews.utils.WindowUtil;

/**
 * Created by Javen on 23/11/2017.
 */

public class NewsDetailsActivity extends AppCompatActivity {
    private WebView webView;
    private FrameLayout containerFrameLayout;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ContentActivityTheme);
        setContentView(R.layout.news_detail_layout);
        initView();
        showContent();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void showContent() {
        Intent intent = getIntent();

        if (intent != null) {
            String webUrl = intent.getStringExtra("webUrl");
            webView.loadUrl(webUrl);
            webView.getSettings().setJavaScriptEnabled(true);
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);  //允许加载javascript
            webSettings.setSupportZoom(true);     //允许缩放
            webSettings.setBuiltInZoomControls(true); //原网页基础上缩放
            webSettings.setUseWideViewPort(true);   //任意比例缩放
        }
    }

    private void initView() {
        WindowUtil.getInstance().setStatusBarTextAndIconDark(NewsDetailsActivity.this);
        initCustomToolBar();

        containerFrameLayout = findViewById(R.id.container);
        fragmentManager = getSupportFragmentManager();

        webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                showErrorFragment();
            }

        });

    }

    private void showErrorFragment() {
        webView.setVisibility(View.GONE);
        containerFrameLayout.setVisibility(View.VISIBLE);

        WebViewErrorFragment fragment = new WebViewErrorFragment();
        fragment.setReLoadClickListener(this::reload);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void reload() {
        if (NetUtil.getInstance().isNetworkConnected(NewsDetailsActivity.this)) {
            containerFrameLayout.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            webView.reload();
        }
    }

    private void initCustomToolBar() {
        CustomToolBar customToolBar = findViewById(R.id.custom_toolbar);
        customToolBar.setNavigationIcon(R.drawable.back_arrow);
        customToolBar.setToolbarType(CustomToolBar.TOOLBAR_NEWS_DETAIL);

        customToolBar.setNavigationOnClickListener(v -> finishCurrentActivity());

        customToolBar.setMenu(R.menu.common_content_menu);
        customToolBar.setMenuListener(item -> {
            DialogUtil.getInstance().showCustomBottomShareDialog(NewsDetailsActivity.this, R.style.ContentActivityBottomDialog, R.layout.share_dialog_layout, R.style.BottomDialog_Animation);
            return false;
        });
    }

    private void finishCurrentActivity() {
        finish();
        overridePendingTransition(0, R.anim.common_right_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finishCurrentActivity();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

}
