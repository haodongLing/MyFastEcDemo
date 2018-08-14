package haodong.com.latte_core.net.callback;

import android.os.Handler;

import haodong.com.latte_core.app.ConfigKeys;
import haodong.com.latte_core.app.Latte;
import haodong.com.latte_core.ui.loader.LatteLoader;
import haodong.com.latte_core.ui.loader.LoaderStyle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUSET;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    // handler 尽量声明为static类型 这样会避免内存泄漏
    private static final Handler HANDLER = Latte.getHandler();

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error,LoaderStyle loaderStyle) {
        this.REQUSET = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE=loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        // 请求成功
            if (response.isSuccessful()){
                if (call.isExecuted()){
                    // 防止空指针
                    if(SUCCESS!=null){
                        SUCCESS.onSuccess(response.body());
                    }
                }
            }else {
                if(ERROR!=null)
                {
                    ERROR.onError(response.code(),response.message());
                }
            }
            // 请求结束
        onRequestFinish();


    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE!=null){
            FAILURE.onFailure();
        }
        if (REQUSET!=null){
            REQUSET.onRequestEnd();
        }
        onRequestFinish();
    }
    private void onRequestFinish() {
        final long delayed = Latte.getConfiguration(ConfigKeys.LOADER_DELAYED);
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            }, delayed);
        }
    }
}
