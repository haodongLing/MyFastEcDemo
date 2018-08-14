package haodong.com.latte_core.util.timer;

import java.util.TimerTask;

public class BaseTimerTask extends TimerTask {
    /**
     * 倒计时结束后，进行回调
     */
    private ITimerListener mITimerListener=null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        // 所有接口回调 首先判断是否是为null
        if (mITimerListener!=null){
            mITimerListener.onTimer();
        }

    }
}
