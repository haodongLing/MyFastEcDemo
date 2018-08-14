package haodong.com.fastecdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import haodong.com.latte.ec.launcher.LuncherDelegate;
import haodong.com.latte.ec.launcher.LuncherScrollDelegate;
import haodong.com.latte.ec.main.EcBottomDelegate;
import haodong.com.latte.ec.main.index.IndexDelegate;
import haodong.com.latte.ec.sign.ISignListener;
import haodong.com.latte.ec.sign.SignUpDelegate;
import haodong.com.latte_core.activities.ProxyActivity;
import haodong.com.latte_core.app.Latte;
import haodong.com.latte_core.delegates.LatteDelegate;
import haodong.com.latte_core.ui.launcher.ILauncherListener;
import haodong.com.latte_core.ui.launcher.OnLauncherFinishTag;
import qiu.niorgai.StatusBarCompat;

public class ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LuncherDelegate();
    }

    @Override
    public void onSignInSuccess() {

    }

    @Override
    public void onSignUpSuccess() {

    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
//                Toast.makeText(getApplicationContext(),"启动结束，用户登录",Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
//                Toast.makeText(getApplicationContext(),"启动结束，用户没登录",Toast.LENGTH_LONG).show();
//                getSupportDelegate().startWithPop(new SignUpDelegate());
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            default:
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
