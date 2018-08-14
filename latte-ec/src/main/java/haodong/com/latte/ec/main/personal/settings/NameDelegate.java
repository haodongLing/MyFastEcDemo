package haodong.com.latte.ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import haodong.com.latte.ec.R;
import haodong.com.latte_core.delegates.LatteDelegate;

/**
 * Created by linghaoDo on 2018/8/12
 */
public class NameDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
//        return R.layout.d;
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
