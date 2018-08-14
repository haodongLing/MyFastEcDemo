package haodong.com.latte.ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import java.util.List;

import butterknife.BindView;
import haodong.com.latte.ec.R;
import haodong.com.latte.ec.R2;
import haodong.com.latte.ec.main.sort.SortDelegate;
import haodong.com.latte.ec.main.sort.content.ContentDelegate;
import haodong.com.latte_core.delegates.LatteDelegate;
import haodong.com.latte_core.ui.recycler.ItemType;
import haodong.com.latte_core.ui.recycler.MultipleFileds;
import haodong.com.latte_core.ui.recycler.MultipleItemEntity;
import haodong.com.latte_core.ui.recycler.MultipleRecycleraAdapter;
import haodong.com.latte_core.ui.recycler.MultipleViewHolder;

/**
 * Created by linghaoDo on 2018/8/8
 */
public class SortRecyclerAdapter extends MultipleRecycleraAdapter {
    private final SortDelegate DELEGATE;
    private int mPreposition=0;


    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
        //添加布局
        addItemType(ItemType.VERTICAL_MENU_LIST,R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST :
                final String myname = entity.getField(MultipleFileds.NAME).toString();
                final Boolean isClicked = entity.getField(MultipleFileds.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_items_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition=holder.getAdapterPosition();
                        if (mPreposition!=currentPosition){
                            // 更新之前的数据
                            getData().get(mPreposition).setField(MultipleFileds.TAG,false);
                            notifyItemChanged(mPreposition);
                            // 更新选中的部分
                            entity.setField(MultipleFileds.TAG,true);
                            notifyItemChanged(currentPosition);
                            mPreposition=currentPosition;
                            final int contentId=getData().get(currentPosition).getField(MultipleFileds.ID);
                            showContent(contentId);

                        }
                    }
                });
                if (!isClicked) {
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));

                } else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }
                holder.setText(R.id.tv_vertical_items_name,myname );
                break;

            default:
                break;

        }

    }
    private void showContent(int contentId){
        final ContentDelegate delegate=ContentDelegate.newInstance(contentId);
        switchDelegate(delegate);

    }
    private void switchDelegate(ContentDelegate delegate){
        // 找到子fragment

        final LatteDelegate contentDelegate=DELEGATE.findChildFragment(ContentDelegate.class);
        if (contentDelegate!=null){
            contentDelegate.replaceFragment(delegate,false);
        }
    }

}
