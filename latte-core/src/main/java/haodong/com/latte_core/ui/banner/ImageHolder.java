package haodong.com.latte_core.ui.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

import haodong.com.latte_core.R;

/**
 * Created by linghaoDo on 2018/8/6
 */
public class ImageHolder implements Holder<String>{
    private AppCompatImageView mImageView=null;

    @Override
    public View createView(Context context) {
        mImageView=new AppCompatImageView(context);
        return mImageView ;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        Glide.with(context)
                .load(data)
                .into(mImageView);

    }
}
