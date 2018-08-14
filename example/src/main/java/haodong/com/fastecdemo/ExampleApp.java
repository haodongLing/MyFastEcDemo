package haodong.com.fastecdemo;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import haodong.com.fastecdemo.event.TestEvent;
import haodong.com.latte.ec.database.DatabaseManager;
import haodong.com.latte.ec.icon.FontEcModule;
import haodong.com.latte_core.app.Latte;
import haodong.com.latte_core.net.AddCookieInterceptor;
import haodong.com.latte_core.net.interceptor.DebugInterceptor;

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withApiHost("http://mock.fulingjie.com/mock/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withIcon(new FontAwesomeModule())
                .withJavascriptInterface("latte")
                .withWebEvent("test", new TestEvent())
                .withInterceptor(new AddCookieInterceptor())
                .configure();
        DatabaseManager.getInstance().init(this);

    }
}
