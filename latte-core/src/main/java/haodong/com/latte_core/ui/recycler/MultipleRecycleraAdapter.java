package haodong.com.latte_core.ui.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import haodong.com.latte_core.R;
import haodong.com.latte_core.ui.banner.BannerCreator;

/**
 * Created by linghaoDo on 2018/8/6
 */
public class MultipleRecycleraAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
        implements BaseQuickAdapter.SpanSizeLookup,
        OnItemClickListener {
    // 确保初始化一次banner 防止重复加载banner
    private boolean mIsInitBanner = false;
    // 加载图片策略
    // 请求的选项
    private static final RequestOptions REQUEST_OPTIONS=
            new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    protected MultipleRecycleraAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    public static MultipleRecycleraAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecycleraAdapter(data);
    }

    public static MultipleRecycleraAdapter create(DataConverter converter) {
        return new MultipleRecycleraAdapter(converter.convert());
    }

    private void init() {
        // 设置不同的布局
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);

        // 设置宽度的监听
        setSpanSizeLookup(this);
        // 动画加载效果
        openLoadAnimation();
        // 多次执行动画
        isFirstOnly(false);
    }

    // 传入现有的viewholder
    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return super.createBaseViewHolder(view);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        final String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;
        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = entity.getField(MultipleFileds.TEXT);
                holder.setText(R.id.text_single, text);
                break;
            case ItemType.IMAGE:
                imageUrl = entity.getField(MultipleFileds.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(REQUEST_OPTIONS)
                        .into((ImageView) holder.getView(R.id.img_single));
                break;
            case ItemType.TEXT_IMAGE:
                text = entity.getField(MultipleFileds.TEXT);
                holder.setText(R.id.tv_multiple, text);
                imageUrl = entity.getField(MultipleFileds.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(REQUEST_OPTIONS)
                        .into((ImageView) holder.getView(R.id.img_multiple));
                break;
            case ItemType.BANNER:
                // 由于recyclerview的item机制  最好有一个判断的
                if (!mIsInitBanner) {
                    bannerImages = entity.getField(MultipleFileds.BANNERS);
                    // ConvenientBanner
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }

                break;
            default:
                break;
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFileds.SPAN_SIZE);
    }
    @Override
    public void onItemClick(int position) {

    }
}
