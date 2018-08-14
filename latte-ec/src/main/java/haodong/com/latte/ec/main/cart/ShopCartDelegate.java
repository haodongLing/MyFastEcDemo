package haodong.com.latte.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import haodong.com.latte.ec.R;
import haodong.com.latte_core.delegates.bottom.BottomItemDelegate;

/**
 * Created by linghaoDo on 2018/8/3
 * 首页
 */

public class ShopCartDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
   return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
