package haodong.com.latte_core.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * Created by linghaoDo on 2018/8/6
 * entity==bean
 */
public class MultipleItemEntity implements MultiItemEntity {
    // 注意 recyclerView在使用时 需要注意内存释放
    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUE = new ReferenceQueue<>();
    // 数据真正的 kay value
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    // key->传入的对象
    // value ->
    private final SoftReference<LinkedHashMap<Object, Object>> FILEDS_REFENRENCE =
            new SoftReference<LinkedHashMap<Object, Object>>(MULTIPLE_FIELDS, ITEM_QUEUE);

    MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        FILEDS_REFENRENCE.get().putAll(fields);
    }

    public static MultipleItemEntityBuilder builder() {
        return new MultipleItemEntityBuilder();
    }

    /**
     * 样式 表现特征
     *
     * @return 类型 最好emum
     */
    @Override
    public int getItemType() {
        return (int) FILEDS_REFENRENCE.get().get(MultipleFileds.ITEM_TYPE);
    }

    // T->泛型类型
    @SuppressWarnings("unchecked")
    public final <T> T getField(Object key) {
        return (T) FILEDS_REFENRENCE.get().get(key);
    }

    public final LinkedHashMap<?, ?> getFields() {
        return FILEDS_REFENRENCE.get();
    }

    public final MultipleItemEntity setField(Object key, Object value) {
        FILEDS_REFENRENCE.get().put(key, value);
        return this;
    }

}
