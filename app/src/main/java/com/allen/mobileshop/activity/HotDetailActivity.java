package com.allen.mobileshop.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.allen.mobileshop.R;
import com.allen.mobileshop.common.Contants;
import com.allen.mobileshop.view.ToolBarX;

/**
 * Created by Allen on 2016/2/17.
 */
public class HotDetailActivity extends BaseActivity {
    private WebView mWebView;
    private ToolBarX toolBarX;

    @Override
    public int getLayoutId() {
        return R.layout.activity_hot_detail_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebView = (WebView) findViewById(R.id.hot_detail_webview);
        initToolBar();
        initWebView();
    }

    private void initToolBar() {
        toolBarX = getToolbar();
        toolBarX.setTitle("商品详情");
    }

    private void initWebView() {
        WebSettings settings = mWebView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(false);
        settings.setAppCacheEnabled(true);

        mWebView.loadUrl(Contants.API.WARES_DETAIL);

    }

}
