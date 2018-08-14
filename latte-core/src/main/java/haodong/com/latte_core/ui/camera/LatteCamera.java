package haodong.com.latte_core.ui.camera;

import android.net.Uri;

import haodong.com.latte_core.delegates.PermissionCheckerDelegate;
import haodong.com.latte_core.util.file.FileUtil;

/**
 * Created by linghaoDo on 2018/8/12
 * 照片调用类
 */
public class LatteCamera {
    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
