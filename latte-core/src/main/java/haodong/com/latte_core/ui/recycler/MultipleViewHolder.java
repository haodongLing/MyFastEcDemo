package haodong.com.latte_core.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by linghaoDo on 2018/8/6
 */
public class MultipleViewHolder extends BaseViewHolder {
    public MultipleViewHolder(View view) {
        super(view);
    }
    public static MultipleViewHolder create(View view){
        return new MultipleViewHolder(view );
    }

}
