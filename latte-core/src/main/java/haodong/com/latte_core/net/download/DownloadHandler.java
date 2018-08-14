package haodong.com.latte_core.net.download;

import android.os.AsyncTask;

import java.util.WeakHashMap;

import haodong.com.latte_core.net.RestCreator;
import haodong.com.latte_core.net.callback.IError;
import haodong.com.latte_core.net.callback.IFailure;
import haodong.com.latte_core.net.callback.IRequest;
import haodong.com.latte_core.net.callback.ISuccess;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadHandler {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownloadHandler(String url,
                           IRequest request,
                           String downDir,
                           String extension,
                           String name,
                           ISuccess success,
                           IFailure failure,
                           IError error) {
        this.URL = url;
        this.REQUEST = request;
        this.DOWNLOAD_DIR = downDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }

    public final void handleDownLoad(){
        if(REQUEST!=null){
            // 开始下载
            REQUEST.onRequestStart();
        }
        RestCreator.getRestService().download(URL,PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            final SaveFileTask saveFileTask=new SaveFileTask(REQUEST,SUCCESS);
                            saveFileTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,DOWNLOAD_DIR,EXTENSION,response,NAME);

                            // 这里一定要注意判断  不然文件下载不全
                            if(saveFileTask.isCancelled()){
                                if (REQUEST!=null){
                                    REQUEST.onRequestEnd();
                                }
                            }
                        }else {
                            if (ERROR!=null){
                                ERROR.onError(response.code(),response.message());
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                            if (FAILURE!=null){
                                FAILURE.onFailure();
                            }
                    }
                });
    }
}
