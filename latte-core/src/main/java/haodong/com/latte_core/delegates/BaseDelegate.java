package haodong.com.latte_core.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import haodong.com.latte_core.activities.ProxyActivity;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * abstract  抽象类
 */
@SuppressWarnings("SpellCheckingInspection")
public abstract class BaseDelegate extends SwipeBackFragment {
    // 可以使用view 或者int来绑定layout
    public abstract Object setLayout();
    private Unbinder mUnbinder=null;
    private final SupportFragmentDelegate DELEGATE = new SupportFragmentDelegate(this);
    private View mRootView = null;
    public abstract void onBindView(@Nullable  Bundle savedInstanceState,@NonNull View rootView);
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View rootView ;
        if(setLayout()instanceof Integer){
            rootView=inflater.inflate((int) setLayout(),container,false);
        }else if(setLayout() instanceof View){
            rootView= (View) setLayout();
        }
        else {
            throw new ClassCastException("setLayout must be int or layout");
        }
            mUnbinder= ButterKnife.bind(this,rootView);
            onBindView(savedInstanceState,rootView);
        return rootView;
    }

    public final ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder!=null){
            //解除绑定
            mUnbinder.unbind();
        }
    }
}
