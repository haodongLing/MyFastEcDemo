package haodong.com.latte_core.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;

/**
 * Created by linghaoDo on 2018/8/9
 * 初始化webview
 * webviewClient
 * webviewChormeClient
 */
public interface IWebViewInitializer {
    WebView initWebView(WebView webView);
    WebViewClient initWebViewClient();
    WebChromeClient initWebChromeClient();

}
