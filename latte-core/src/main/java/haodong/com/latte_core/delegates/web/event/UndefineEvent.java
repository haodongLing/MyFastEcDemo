package haodong.com.latte_core.delegates.web.event;


import haodong.com.latte_core.util.log.LatteLogger;

/**
 * Created by linghaoDo on 2018/8/10
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
