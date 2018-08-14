package haodong.com.latte_core.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * 以一种缓存的方式来创建loader
 */

public final class LoaderCreater {
    private static final WeakHashMap<String,Indicator>LOCDING_MAP=new WeakHashMap<>();
    static AVLoadingIndicatorView create(String type,Context context){
            final AVLoadingIndicatorView avLoadingIndicatorView=new AVLoadingIndicatorView(context);
            if (LOCDING_MAP.get(type)==null){
                final Indicator indicator=getIndicator(type);
                LOCDING_MAP.put(type,indicator);
            }
            avLoadingIndicatorView.setIndicator(LOCDING_MAP.get(type));
            return avLoadingIndicatorView;
    }
    private static Indicator getIndicator(String name ){
        if(name==null||name.isEmpty()){
            return null;
        }
        final StringBuilder drawbleClassName=new StringBuilder();
        if(!name.contains(".")){
            final   String  defaultPakageName=AVLoadingIndicatorView.class.getPackage().getName();
            drawbleClassName.append(defaultPakageName)
                    .append(".indicators")
                    .append(".");
        }
        drawbleClassName.append(name);
        try {
            final Class<?>drawbleClass=Class.forName(drawbleClassName.toString());
            return (Indicator) drawbleClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
