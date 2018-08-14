package haodong.com.latte_core.ui.launcher;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder>{

    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
