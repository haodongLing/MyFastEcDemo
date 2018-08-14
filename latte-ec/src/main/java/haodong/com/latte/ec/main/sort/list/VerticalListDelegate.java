package haodong.com.latte.ec.main.sort.list;

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
import haodong.com.latte.ec.main.sort.SortDelegate;
import haodong.com.latte_core.delegates.LatteDelegate;
import haodong.com.latte_core.net.RestClient;
import haodong.com.latte_core.net.callback.ISuccess;
import haodong.com.latte_core.ui.recycler.MultipleItemEntity;

/**
 * Created by linghaoDo on 2018/8/7
 */
public class VerticalListDelegate extends LatteDelegate {
    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        // 不要动画
        mRecyclerView.setItemAnimator(null);


    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initRecyclerView();


    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        // 数据处理
        RestClient.builder()
                .url("data/sort_list_data.json")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final List<MultipleItemEntity> data=new VerticalListDataConverter()
                                .setJsonData(response)
                                .convert();
                        final SortDelegate delegate=getParentDelegate();
                        final SortRecyclerAdapter adapter=new SortRecyclerAdapter(data,delegate);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
