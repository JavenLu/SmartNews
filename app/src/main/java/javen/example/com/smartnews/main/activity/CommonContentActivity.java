package javen.example.com.smartnews.main.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import javen.example.com.smartnews.R;
import javen.example.com.smartnews.custom_view.CustomToolBar;

/**
 * Created by Javen on 23/11/2017.
 */

public class CommonContentActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_content_layout);
        initView();
        showContent();
    }

    private void showContent() {
        Intent intent = getIntent();
        if (intent != null) {
            String webUrl = intent.getStringExtra("webUrl");
            webView.loadUrl(webUrl);
        }
    }

    private void initView() {
        initCustomToolBar();

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
        });

    }

    private void initCustomToolBar() {
        CustomToolBar customToolBar = findViewById(R.id.custom_toolbar);
        customToolBar.setNavigationIcon(R.drawable.back_arrow);
        customToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishCurrentActivity();
            }
        });

        customToolBar.setMenu(R.menu.common_content_menu);
        customToolBar.setMenuListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(CommonContentActivity.this, "弹出分享对话框", Toast.LENGTH_SHORT).show();
                return false;
            }
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
