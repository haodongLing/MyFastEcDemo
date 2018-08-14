package haodong.com.latte.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import haodong.com.latte.ec.R;
import haodong.com.latte.ec.R2;
import haodong.com.latte.ec.main.personal.PersonalDelegate;
import haodong.com.latte_core.delegates.LatteDelegate;
import haodong.com.latte_core.net.RestClient;
import haodong.com.latte_core.net.callback.ISuccess;
import haodong.com.latte_core.ui.recycler.MultipleItemEntity;

/**
 * Created by linghaoDo on 2018/8/11
 */
public class OrderListDelegate extends LatteDelegate {
    private String mType = null;
    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;
//    public static final String ORDER_TYPE = "ORDER_TYPE";

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mType = args.getString(PersonalDelegate.ORDER_TYPE);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .loader(getContext())
                .url("data/order_list.json")
                .params("type", mType)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data = new OrderListDataConverter().setJsonData(response).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }


}
