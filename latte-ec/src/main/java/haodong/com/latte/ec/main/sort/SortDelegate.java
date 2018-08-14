package haodong.com.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import haodong.com.latte.ec.R;
import haodong.com.latte.ec.main.sort.content.ContentDelegate;
import haodong.com.latte.ec.main.sort.list.VerticalListDelegate;
import haodong.com.latte_core.delegates.LatteDelegate;
import haodong.com.latte_core.delegates.bottom.BottomItemDelegate;

/**
 * Created by linghaoDo on 2018/8/3
 */
public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate=new VerticalListDelegate();
        loadRootFragment(R.id.vertical_list_container,listDelegate);
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));


    }
}
