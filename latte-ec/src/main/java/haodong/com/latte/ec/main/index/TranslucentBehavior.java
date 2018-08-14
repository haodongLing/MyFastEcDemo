package haodong.com.latte.ec.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import haodong.com.latte.ec.R;
import haodong.com.latte_core.app.Latte;
import haodong.com.latte_core.ui.recycler.RgbValue;

/**
 * Created by linghaoDo on 2018/8/7
 */
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    //顶部距离
    private int mDistanceY=0;
    //颜色变化的速率
    private static final int SHOW_SPEED=3;
    //定义颜色
    private final RgbValue RGB_VALUE=RgbValue.create(255,124,2);

    //注意这个变量一定要定义成类变量
    private int mOffset = 0;
    //延长滑动过程
    private static final int MORE = 100;

    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**

     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        // 依赖的recyclerview
        return dependency.getId() == R.id.rv_index;
    }

    /**
     *
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param axes
     * @param type
     * @return
     */
    @Override
    public boolean onStartNestedScroll(
            @NonNull CoordinatorLayout coordinatorLayout
            , @NonNull Toolbar child
            , @NonNull View directTargetChild
            , @NonNull View target
            , int axes
            , int type) {
        // 接管事件
        return true;
    }

//    @Override
//    public void onNestedScroll(
//            @NonNull CoordinatorLayout coordinatorLayout
//            , @NonNull Toolbar toolbar
//            , @NonNull View target
//            , int dxConsumed
//            , int dyConsumed
//            , int dxUnconsumed
//            , int dyUnconsumed
//            , int type) {
//        final int startOffset = 0;
//        final Context context = Latte.getApplicationContext();
//        final int endOffset = context.getResources().getDimensionPixelOffset(R.dimen.header_height) + MORE;
//        mOffset += dyConsumed;
//        if (mOffset <= startOffset) {
//            toolbar.getBackground().setAlpha(0);
//        } else if (mOffset > startOffset && mOffset < endOffset) {
//            final float percent = (float) (mOffset - startOffset) / endOffset;
//            final int alpha = Math.round(percent * 255);
//            toolbar.getBackground().setAlpha(alpha);
//        } else if (mOffset >= endOffset) {
//            toolbar.getBackground().setAlpha(255);
//        }
//    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                  @NonNull Toolbar child,
                                  @NonNull View target,
                                  int dx,
                                  int dy,
                                  @NonNull int[] consumed,
                                  int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        //增加滑动距离
        mDistanceY+=dy;
        //toolbar的高度
        final int targetHeight=child.getBottom();
        //当滑动时，并且距离小于toolbar的高度，调整渐变色
        if (mDistanceY>0&&mDistanceY<=targetHeight){
                final float scale=(float) mDistanceY/targetHeight;
                final float alpha=scale*255;
                child.setBackgroundColor(Color.argb((int) alpha,RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));

        }else if (mDistanceY>targetHeight){
            child.setBackgroundColor(Color.rgb(RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));
        }

    }
}
