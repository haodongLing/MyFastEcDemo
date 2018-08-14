package haodong.com.latte.ec.main.personal.order;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import haodong.com.latte.ec.R;
import haodong.com.latte_core.ui.recycler.MultipleFileds;
import haodong.com.latte_core.ui.recycler.MultipleItemEntity;
import haodong.com.latte_core.ui.recycler.MultipleRecycleraAdapter;
import haodong.com.latte_core.ui.recycler.MultipleViewHolder;

/**
 * Created by linghaoDo on 2018/8/11
 */
public class OrderListAdapter extends MultipleRecycleraAdapter {
    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    protected OrderListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(OrderListItemType.ITEM_ORDER_LIST, R.layout.item_order_list);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case OrderListItemType.ITEM_ORDER_LIST:
                final AppCompatImageView imageView = holder.getView(R.id.image_order_list);
                final AppCompatTextView title = holder.getView(R.id.tv_order_list_title);
                final AppCompatTextView time = holder.getView(R.id.tv_order_list_time);
                final AppCompatTextView price = holder.getView(R.id.tv_order_list_price);

                final String titleVal = entity.getField(MultipleFileds.TITLE);
                final String timeVal = entity.getField(OrderItemFields.TIME);
                final double priceVal = entity.getField(OrderItemFields.PRICE);
                final String imageUrl = entity.getField(MultipleFileds.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(OPTIONS)
                        .into(imageView);


                title.setText(titleVal);
                time.setText(timeVal);
                price.setText("价格"+String.valueOf(priceVal));
                time.setText("时间："+timeVal);

                break;
            default:
                break;
        }
    }
}
