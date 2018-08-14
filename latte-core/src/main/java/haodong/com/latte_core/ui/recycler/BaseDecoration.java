package haodong.com.latte_core.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by linghaoDo on 2018/8/7
 * 基础装饰
 */
public class BaseDecoration extends DividerItemDecoration{
    public BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color,size));
    }
    public static BaseDecoration create(@ColorInt int color, int size){
        return new BaseDecoration(color,size);
    }

}
