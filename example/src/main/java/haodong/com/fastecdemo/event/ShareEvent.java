package haodong.com.fastecdemo.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import haodong.com.latte_core.delegates.web.event.Event;
import haodong.com.latte_core.util.log.LatteLogger;


/**
 *  Created by linghaoDo on 2018/8/10
 */

public class ShareEvent extends Event {

    @Override
    public String execute(String params) {

        LatteLogger.json("ShareEvent", params);
        final JSONObject object = JSON.parseObject(params).getJSONObject("params");
        final String title = object.getString("title");
        final String url = object.getString("url");
        final String imageUrl = object.getString("imageUrl");
        final String text = object.getString("text");

//        ShareSDK.initSDK(getContext());
//        final OnekeyShare oks = new OnekeyShare();
//        oks.disableSSOWhenAuthorize();
//        oks.setTitle(title);
//        oks.setText(text);
//        oks.setImageUrl(imageUrl);
//        oks.setUrl(url);
//        oks.show(getContext());

        return null;
    }
}
