package haodong.com.latte.ec.main.sort.content;

import android.support.v7.widget.AppCompatImageView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import haodong.com.latte.ec.R;

/**
 * Created by linghaoDo on 2018/8/8
 */
public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean,BaseViewHolder> {
    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();
    /**
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    /**
     * 头数据的转化
     * @param helper
     * @param item
     */
    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
        helper.setText(R.id.header,item.header);
        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionBean item) {
        // item.t 返回sectionbean 类型
        final String thumb=item.t.getGoodsThumb();
        final String name=item.t.getGoodsName();
        final int goodsId=item.t.getGoodsId();
        final SectionContentItemEntity entity=item.t;
        helper.setText(R.id.tv,name);
        final AppCompatImageView goodsImageView = helper.getView(R.id.iv);
        Glide.with(mContext)
                .load(thumb)
                .into(goodsImageView);

    }
}
