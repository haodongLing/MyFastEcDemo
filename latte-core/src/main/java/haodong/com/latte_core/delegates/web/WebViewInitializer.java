package haodong.com.latte_core.delegates.web;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by linghaoDo on 2018/8/10
 */
public class WebViewInitializer {

    @SuppressLint("SetJavaScriptEnabled")
    public WebView creatWebView(WebView webView) {
        // 可调式化
        WebView.setWebContentsDebuggingEnabled(true);
        //cookie
        final CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }
        CookieManager.setAcceptFileSchemeCookies(true);
        // 不能横向滚动
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        // 截图
        webView.setDrawingCacheEnabled(true);
        // 屏蔽长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        // 初始化Websetting
        final WebSettings settings = webView.getSettings();

        final String ua = settings.getUserAgentString();
        settings.setJavaScriptEnabled(true);
        settings.setUserAgentString(ua + "Latte");
        // 隐藏缩放控件
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        //禁止缩放
        settings.setSupportZoom(false);
        //文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowFileAccessFromFileURLs(true);
        //缓存相关
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        return webView;
    }
}
