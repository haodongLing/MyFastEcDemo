package haodong.com.latte.ec.main.sort.list;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import haodong.com.latte_core.ui.recycler.DataConverter;
import haodong.com.latte_core.ui.recycler.ItemType;
import haodong.com.latte_core.ui.recycler.MultipleFileds;
import haodong.com.latte_core.ui.recycler.MultipleItemEntity;

/**
 * Created by linghaoDo on 2018/8/8
 */
public final class VerticalListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> datalist = new ArrayList<>();
        final JSONArray dataArray = JSON
                .parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String mName = data.getString("name");
            //tag 标记第一个数据是被点击的
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFileds.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFileds.ID, id)
                    .setField(MultipleFileds.NAME, mName)
                    .setField(MultipleFileds.TAG, false)
                    .build();
            ENTITIES.add(entity);
            //设置tag
            ENTITIES.get(0).setField(MultipleFileds.TAG, true);

        }
        return ENTITIES;
    }
}
