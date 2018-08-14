package haodong.com.latte_core.net;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import haodong.com.latte_core.net.callback.IError;
import haodong.com.latte_core.net.callback.IFailure;
import haodong.com.latte_core.net.callback.IRequest;
import haodong.com.latte_core.net.callback.ISuccess;
import haodong.com.latte_core.net.callback.RequestCallbacks;
import haodong.com.latte_core.net.download.DownloadHandler;
import haodong.com.latte_core.ui.loader.LatteLoader;
import haodong.com.latte_core.ui.loader.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import okhttp3.RequestBody;

/**
 * 进行请求的具体实现类
 */
public class RestClient {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    // 使用final时  注意必须使用构造方法进行赋值。
    // 注意  当太长时，注意换行。
    public RestClient(String url,
                      Map<String, Object> params,
                      String downloadDir,
                      String extension,
                      String name,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      Context context,
                      LoaderStyle loaderStyle
    ) {
        this.URL = url;
        PARAMS.putAll(params);
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    /**
     * 建造者
     *
     * @return
     */
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        //设置loading
        if (LOADER_STYLE!=null){
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call=service.get(URL,PARAMS);
                break;
            case POST:
                call=service.post(URL,PARAMS);
                break;
            case POST_RAW:
                call=service.postRaw(URL,BODY);
                break;
            case PUT:
                call=service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                call=service.postRaw(URL,BODY);
            case DELETE:
                call=service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService().upload(URL,body);
            default:
                break;
        }
        if(call!=null){
            /**
             *
             */
            call.enqueue(getResponseCallback());
        }
    }
   private Callback<String> getResponseCallback(){
        return (Callback<String>) new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
   }
    /**
     * 具体的使用方法
     */
    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
//        request(HttpMethod.POST);
        if(BODY==null){
            request(HttpMethod.POST);
        }else {
            if(!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }
    public final void put(){
//
        if (BODY == null) {

            request(HttpMethod.PUT);
        }else {
            if(!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAW);
        }
    }
    public final void download(){
        new DownloadHandler(URL,REQUEST,DOWNLOAD_DIR,EXTENSION,NAME,SUCCESS,FAILURE,ERROR).handleDownLoad();
    }




}
