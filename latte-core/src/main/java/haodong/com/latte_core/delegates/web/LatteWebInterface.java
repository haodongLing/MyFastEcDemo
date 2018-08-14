package haodong.com.latte_core.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;

import haodong.com.latte_core.delegates.web.event.Event;
import haodong.com.latte_core.delegates.web.event.EventManager;
import haodong.com.latte_core.util.log.LatteLogger;

/**
 * Created by linghaoDo on 2018/8/9
 */
public class LatteWebInterface {
    private final WebDelegate DELEGATE;

    public LatteWebInterface(WebDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }
    static LatteWebInterface create(WebDelegate delegate){
        return new LatteWebInterface(delegate);
    }
    @JavascriptInterface
    public String event(String params){
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        LatteLogger.d("WEB_EVENT",params);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }


}
