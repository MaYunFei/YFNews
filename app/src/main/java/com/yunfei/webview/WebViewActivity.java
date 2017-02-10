package com.yunfei.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.yunfei.core.mvp.core.BaseActivity;
import com.yunfei.yfnews.R;

public class WebViewActivity extends BaseActivity {

  private static final String EXTRA_URL = "extra_url";
  private static final String EXTRA_TITLE = "extra_title";

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.progressbar_webview) ProgressBar mProgressbarWebview;
  @BindView(R.id.web_view) WebView mWebView;

  public static void startWebViewActivity(Context context, String title, String url) {
    Intent intent = new Intent(context, WebViewActivity.class);
    intent.putExtra(EXTRA_URL, url);
    intent.putExtra(EXTRA_TITLE, title);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initView();
  }

  private void initView() {
    setSupportActionBar(mToolbar);
    mToolbar.setNavigationIcon(R.drawable.ic_clear);
    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        finish();
      }
    });
    setTitle(getIntent().getStringExtra(EXTRA_TITLE));

    initWebView();
    loadUrl(getIntent().getStringExtra(EXTRA_URL));
  }

  @Override protected int getContentLayoutId() {
    return R.layout.web_view_activity;
  }

  public void loadUrl(String url) {
    mWebView.loadUrl(url);
  }

  public void setTitle(String title) {
    getSupportActionBar().setTitle(title);
  }

  public void initWebView() {
    WebSettings settings = mWebView.getSettings();
    settings.setLoadWithOverviewMode(true);
    settings.setJavaScriptEnabled(true);
    settings.setAppCacheEnabled(true);
    settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    settings.setSupportZoom(true);

    mWebView.setWebChromeClient(new MyWebChrome());
    mWebView.setWebViewClient(new MyWebClient());
  }

  class MyWebChrome extends WebChromeClient {
    @Override public void onProgressChanged(WebView view, int newProgress) {
      mProgressbarWebview.setVisibility(View.VISIBLE);
      mProgressbarWebview.setProgress(newProgress);
    }
  }

  class MyWebClient extends WebViewClient {
    @Override public void onPageFinished(WebView view, String url) {
      mProgressbarWebview.setVisibility(View.GONE);
    }
  }

  @Override protected void onPause() {
    mWebView.onPause();
    super.onPause();
  }

  @Override protected void onDestroy() {
    mWebView.destroy();
    super.onDestroy();
  }

  @Override public void onBackPressed() {
    if (mWebView.canGoBack()) {
      mWebView.goBack();
    } else {
      finish();
    }
  }
}
