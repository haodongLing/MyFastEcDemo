package haodong.com.latte_core.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import haodong.com.latte_core.app.ConfigKeys;
import haodong.com.latte_core.app.Latte;
import haodong.com.latte_core.delegates.web.IPageLoadListener;
import haodong.com.latte_core.delegates.web.WebDelegate;
import haodong.com.latte_core.delegates.web.route.Router;
import haodong.com.latte_core.ui.loader.LatteLoader;
import haodong.com.latte_core.util.log.LatteLogger;
import haodong.com.latte_core.util.storage.LattePreference;

/**
 * Created by linghaoDo on 2018/8/10
 */
public class WebViewClientImpl extends WebViewClient {
    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static Handler handler = new Handler();

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }


    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("ShouldOverideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }
    //获取浏览器cookie
    private void syncCookie() {
        final CookieManager manager = CookieManager.getInstance();
        /*
          注意，这里的Cookie和API请求的Cookie是不一样的，这个在网页不可见
         */
        final String webHost = Latte.getConfiguration(ConfigKeys.WEB_HOST);
        if (webHost != null) {
            if (manager.hasCookies()) {
                final String cookieStr = manager.getCookie(webHost);
                if (cookieStr != null && !cookieStr.equals("")) {
                    LattePreference.addCustomAppProfile("cookie", cookieStr);
                }
            }
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        }, 1000);

    }
}
