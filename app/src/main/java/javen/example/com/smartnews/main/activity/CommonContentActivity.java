package javen.example.com.smartnews.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebView;

import javen.example.com.smartnews.R;

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
        webView = findViewById(R.id.web_view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
            overridePendingTransition(0, R.anim.common_right_out);
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
