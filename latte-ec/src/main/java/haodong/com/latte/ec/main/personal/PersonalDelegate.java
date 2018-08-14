package haodong.com.latte.ec.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import haodong.com.latte.ec.R;
import haodong.com.latte.ec.R2;
import haodong.com.latte.ec.main.personal.list.ListAdapter;
import haodong.com.latte.ec.main.personal.list.ListBean;
import haodong.com.latte.ec.main.personal.list.ListItemType;
import haodong.com.latte.ec.main.personal.order.OrderListDelegate;
import haodong.com.latte.ec.main.personal.profile.UserProfileDelegate;
import haodong.com.latte_core.delegates.bottom.BottomItemDelegate;

/**
 * Created by linghaoDo on 2018/8/3
 */
public class PersonalDelegate extends BottomItemDelegate {
    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle mArgs = null;
    private long TOUCH_TIME = 0;
    private static final long WAIT_TIME = 2000L;
    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings = null;

    @OnClick(R2.id.tv_all_order)
    void onClickAllOrder() {
        mArgs.putString(ORDER_TYPE, "all");
        startOrderListByType();
    }
    @OnClick(R2.id.img_user_avatar)
    void onClickAvator(){
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //bundle的初始化
        mArgs = new Bundle();
    }

    private void startOrderListByType() {
        OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
//                .setDelegate(new AddressDelegate())
                .setText("收货地址")
                .build();

        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
//                .setDelegate(new SettingsDelegate())
                .setText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);
    }

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + "Latte", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
