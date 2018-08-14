package haodong.com.latte.ec.launcher;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

import haodong.com.latte.ec.R;
import haodong.com.latte_core.app.AccuntManager;
import haodong.com.latte_core.app.IUserChecker;
import haodong.com.latte_core.delegates.LatteDelegate;
import haodong.com.latte_core.ui.launcher.ILauncherListener;
import haodong.com.latte_core.ui.launcher.LauncherHolderCreator;
import haodong.com.latte_core.ui.launcher.OnLauncherFinishTag;
import haodong.com.latte_core.ui.launcher.ScrollLauncherTag;
import haodong.com.latte_core.util.storage.LattePreference;

public class LuncherScrollDelegate extends LatteDelegate implements OnItemClickListener {
    private ConvenientBanner<Integer> mConvientBanner=null;
    private static final ArrayList<Integer>INTEGERS=new ArrayList<>();
    private ILauncherListener mILauncherListener=null;

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvientBanner.setPages(new LauncherHolderCreator(),INTEGERS)
        .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
        .setOnItemClickListener(this)
        .setCanLoop(false);
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
        mConvientBanner=new ConvenientBanner<>(getContext());
        return mConvientBanner;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        initBanner();
    }


    @Override
    public void onItemClick(int position) {
        if(position==(INTEGERS.size()-1)){
           LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //检查用户是否已经登录了，
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
}
