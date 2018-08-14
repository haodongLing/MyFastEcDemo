package haodong.com.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import haodong.com.latte.ec.R;
import haodong.com.latte.ec.R2;
import haodong.com.latte.ec.main.EcBottomDelegate;
import haodong.com.latte_core.delegates.bottom.BottomItemDelegate;
import haodong.com.latte_core.net.RestClient;
import haodong.com.latte_core.net.callback.ISuccess;
import haodong.com.latte_core.ui.recycler.BaseDecoration;
import haodong.com.latte_core.ui.refresh.RefreshHanlder;

/**
 * Created by linghaoDo on 2018/8/3
 * 首页
 */

public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;
    private RefreshHanlder mRefreshHanlder = null;

    //    @BindView(R2.id.icon_index_message)
//    IconTextView
    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRefreshHanlder = RefreshHanlder.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());
        RestClient.builder()
                .url("index.php")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .build()
                .get();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHanlder.firstPage("api");
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background),4));
        // 获取容器
        final EcBottomDelegate ecBottomDelegate=getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;

    }


}
