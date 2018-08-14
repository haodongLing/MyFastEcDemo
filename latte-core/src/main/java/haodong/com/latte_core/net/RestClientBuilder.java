package haodong.com.latte_core.net;

import android.content.Context;

import java.io.File;
import java.util.WeakHashMap;

import haodong.com.latte_core.net.callback.IError;
import haodong.com.latte_core.net.callback.IFailure;
import haodong.com.latte_core.net.callback.IRequest;
import haodong.com.latte_core.net.callback.ISuccess;
import haodong.com.latte_core.ui.loader.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 一些传值的操作
 */
public class RestClientBuilder {
    // 命名规范 如果是类变量，最好加上m
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private String mUrl = null;
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    // 注意处理重载
    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    //参数的重载
    public final RestClientBuilder params(String key, Object value) {
        //不允许传入的参数的空
            PARAMS.put(key, value);
        return this;
    }

    // 原始数据处理
    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), raw);
        return this;
    }

    //
    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    //
    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }
    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    //
    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    //
    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS,
                mDownloadDir, mExtension, mName,
                mIRequest, mISuccess, mIFailure,
                mIError, mBody, mFile, mContext,
                mLoaderStyle);
    }
    public final RestClientBuilder loader(Context context){
        this.mContext=context;
        this.mLoaderStyle=LoaderStyle.BallClipRotatePulseIndicator;
        //可以使用默认形式
        return this;
    }
    private void request(HttpMethod method){
        final RestService service=RestCreator.getRestService();
    }

}
