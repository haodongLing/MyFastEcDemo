package haodong.com.latte_core.ui.recycler;

import android.support.annotation.NonNull;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * Created by linghaoDo on 2018/8/6
 * 建造者  可写成静态内部类
 */
public class MultipleItemEntityBuilder {
    private static final LinkedHashMap<Object,Object>FIELDS=new LinkedHashMap<>();
    public MultipleItemEntityBuilder(){
        // 先清除
        FIELDS.clear();
    }
    public final MultipleItemEntityBuilder setItemType(int itemType){
        FIELDS.put(MultipleFileds.ITEM_TYPE,itemType);
        return this;
    }
    public final MultipleItemEntityBuilder setField(Object key,Object value){
        FIELDS.put(key,value);
        return this;
    }
    public final MultipleItemEntityBuilder setField(LinkedHashMap<?,?>map){
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build(){
       return new MultipleItemEntity(FIELDS);
    }

}
