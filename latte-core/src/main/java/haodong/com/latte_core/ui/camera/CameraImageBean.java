package haodong.com.latte_core.ui.camera;

import android.net.Uri;

/**
 * Created by linghaoDo on 2018/8/12
 * 存储一些中间值
 */
public final class CameraImageBean {
    private Uri mPath = null;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }
}
