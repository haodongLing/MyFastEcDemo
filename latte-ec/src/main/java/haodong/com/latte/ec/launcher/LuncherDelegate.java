package haodong.com.latte.ec.launcher;

import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import haodong.com.latte.ec.R;
import haodong.com.latte.ec.R2;
import haodong.com.latte_core.app.AccuntManager;
import haodong.com.latte_core.app.IUserChecker;
import haodong.com.latte_core.delegates.LatteDelegate;
import haodong.com.latte_core.ui.launcher.ILauncherListener;
import haodong.com.latte_core.ui.launcher.OnLauncherFinishTag;
import haodong.com.latte_core.ui.launcher.ScrollLauncherTag;
import haodong.com.latte_core.util.storage.LattePreference;
import haodong.com.latte_core.util.timer.BaseTimerTask;
import haodong.com.latte_core.util.timer.ITimerListener;

public class LuncherDelegate extends LatteDelegate implements ITimerListener{
    private Timer mTimer=null;
    private int mCount=4;
    private ILauncherListener mILauncherListener = null;

    @BindView(R2.id.tv_launcher_timer) AppCompatTextView mTvTimer;
    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerVirew(){
        if(mTimer!=null){
            mTimer.cancel();
            mTimer=null;
            checkIsShowScroll();
        }
    }
    private void initTimer(){
        mTimer=new Timer();
        BaseTimerTask task =new BaseTimerTask(this);
        /**
         * @param 执行的任务
         * @param 每隔一段时间执行一次
         * @param 持续的时间
         */
        mTimer.schedule(task,0,1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mILauncherListener= (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        initTimer();
    }
    // 判断是否开启轮播图 start详细理解下
    private void checkIsShowScroll(){
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
         start(new LuncherScrollDelegate(),SINGLETASK);
        }else {
             //检查用户是否登录了app
            //检查用户是否登录了APP
            AccuntManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });

        }
    }


    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mTvTimer!=null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if (mCount<0){
                        if(mTimer!=null){
                            mTimer.cancel();
                            mTimer=null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });

    }
}
