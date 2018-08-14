package haodong.com.latte_core.net;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.io.IOException;

import haodong.com.latte_core.util.storage.LattePreference;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by linghaoDo on 2018/8/10
 */

public final class AddCookieInterceptor implements Interceptor {

    @SuppressLint("CheckResult")
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        Observable
                .just(LattePreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull String cookie) throws Exception {
                        //给原生API请求附带上WebView拦截下来的Cookie
                        builder.addHeader("Cookie", cookie);
                    }
                });
        return chain.proceed(builder.build());
    }
}
