package haodong.com.latte.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import haodong.com.latte.ec.detail.GoodsDetailDelegate;
import haodong.com.latte_core.delegates.LatteDelegate;
import haodong.com.latte_core.ui.recycler.MultipleFileds;
import haodong.com.latte_core.ui.recycler.MultipleItemEntity;

/**
 * Created by linghaoDo on 2018/8/7
 */
public class IndexItemClickListener extends SimpleClickListener {
    private final LatteDelegate DELEGATE;

    private IndexItemClickListener(LatteDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }
    public  static SimpleClickListener create(LatteDelegate delegate){
        return new IndexItemClickListener(delegate);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final int goodsId = entity.getField(MultipleFileds.ID);
        final GoodsDetailDelegate delegate=GoodsDetailDelegate.create(goodsId);
        DELEGATE.getSupportDelegate().start(delegate);

    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
