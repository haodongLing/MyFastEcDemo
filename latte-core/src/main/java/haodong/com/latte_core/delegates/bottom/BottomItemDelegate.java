package haodong.com.latte_core.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import haodong.com.latte_core.R;
import haodong.com.latte_core.delegates.LatteDelegate;

/**
 * Created by linghaoDo on 2018/8/2
 */
public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener{
    private long mExitTime=0;
    private static final int EXIT_TIME=2000;

    @Override
    public void onResume() {
        super.onResume();
        final View rootView=getView();
        if (rootView!=null){
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK&&event.getAction()==event.ACTION_DOWN){
            if((System.currentTimeMillis()-mExitTime)>mExitTime){
                Toast.makeText(getContext(),"双击退出"+getString(R.string.app_name),Toast.LENGTH_SHORT).show();
                mExitTime=System.currentTimeMillis();
            }else {
                _mActivity.finish();
                if (mExitTime!=0){
                    mExitTime=0;
                }
            }
            return true;
        }
        return false;
    }
}
