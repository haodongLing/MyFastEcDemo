package haodong.com.latte_core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.logging.Handler;

import haodong.com.latte_core.app.Latte;
import haodong.com.latte_core.net.RestClient;
import haodong.com.latte_core.net.callback.ISuccess;
import haodong.com.latte_core.ui.recycler.DataConverter;
import haodong.com.latte_core.ui.recycler.MultipleRecycleraAdapter;

/**
 * Created by linghaoDo on 2018/8/4
 */
public class RefreshHanlder implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener{
    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PageingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecycleraAdapter mAdapter = null;
    private final DataConverter CONVERTER;

    public RefreshHanlder(SwipeRefreshLayout swipeRefreshLayout,
                          RecyclerView recyclerView,
                          DataConverter converter,
                          PageingBean bean) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.RECYCLERVIEW=recyclerView;
        this.CONVERTER=converter;
        this.BEAN=bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHanlder create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView,
                                        DataConverter converter
                                        ) {
        return new RefreshHanlder(swipeRefreshLayout, recyclerView, converter, new PageingBean());
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
//        Latte.getHandler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                REFRESH_LAYOUT.setRefreshing(false);
//            }
//        },2000);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        Toast.makeText(Latte.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        final JSONObject object=JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                              // 设置adapter
                        mAdapter=MultipleRecycleraAdapter.create(CONVERTER.setJsonData(response));
                        //上拉加载
                        mAdapter.setOnLoadMoreListener(RefreshHanlder.this,RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
