package haodong.com.fastecdemo.event;

import android.webkit.WebView;
import android.widget.Toast;

import haodong.com.latte_core.delegates.web.event.Event;


/**
 *  Created by linghaoDo on 2018/8/10
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_LONG).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }
        return null;
    }
}
