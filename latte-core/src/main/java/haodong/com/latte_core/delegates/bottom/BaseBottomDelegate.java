package haodong.com.latte_core.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import haodong.com.latte_core.R;
import haodong.com.latte_core.R2;
import haodong.com.latte_core.delegates.LatteDelegate;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by linghaoDo on 2018/8/2
 */
public abstract class BaseBottomDelegate extends LatteDelegate
implements View.OnClickListener
{
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<BottomItemDelegate> ITEMS_DELEGATES = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    //申明变量时劲量给定一个初值
    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.RED;
    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottombar;

    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder itemBuilder);

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if (mIndexDelegate != 0) {
            mClickedColor = setClickedColor();
        }
        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);
            ITEMS_DELEGATES.add(value);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        // 注意 将size写出来  这样可以很好地提升一些小小的性能
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottombar);
            final RelativeLayout item= (RelativeLayout) mBottombar.getChildAt(i);
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView itemIcon= (IconTextView) item.getChildAt(0);
            final TextView itemText= (TextView) item.getChildAt(1);
            final BottomTabBean bean=TAB_BEANS.get(i);
            // 初始化数据
            itemIcon.setText(bean.getIcon());
            itemText.setText(bean.getTitle());
            if (i==mIndexDelegate){
                itemIcon.setTextColor(mClickedColor);
                itemText.setTextColor(mClickedColor);
            }
        }
        final SupportFragment[] delegateArray=ITEMS_DELEGATES.toArray(new SupportFragment[size]);
        loadMultipleRootFragment(R.id.bottom_bar_delegate_container,mIndexDelegate,delegateArray);
     }
     private void resetColor(){
        final int count=mBottombar.getChildCount();
        for (int i=0;i<count;i++){
            final RelativeLayout item= (RelativeLayout) mBottombar.getChildAt(i);
            final IconTextView itemIcon= (IconTextView) item.getChildAt(0);
            final TextView itemText= (TextView) item.getChildAt(1);
                itemIcon.setTextColor(Color.GRAY);
                itemText.setTextColor(Color.GRAY);
        }
     }

    @Override
    public void onClick(View v) {
        final int tag= (int) v.getTag();
        resetColor();
        final RelativeLayout item= (RelativeLayout) v;
        final IconTextView itemIcon= (IconTextView) item.getChildAt(0);
        final TextView itemText= (TextView) item.getChildAt(1);
        itemIcon.setTextColor(mClickedColor);
        itemText.setTextColor(mClickedColor);
        // 关键点  注意该方法的先后顺序
        showHideFragment(ITEMS_DELEGATES.get(tag),ITEMS_DELEGATES.get(mCurrentDelegate));
        //
        mCurrentDelegate=tag;

    }
}
