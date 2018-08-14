package haodong.com.latte.ec.main.personal.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import haodong.com.latte_core.ui.recycler.DataConverter;
import haodong.com.latte_core.ui.recycler.MultipleFileds;
import haodong.com.latte_core.ui.recycler.MultipleItemEntity;

/**
 * Created by linghaoDo on 2018/8/11
 */
public class OrderListDataConverter  extends DataConverter{
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray array=JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size=array.size();
        for(int i=0;i<size;i++){
                final JSONObject data=array.getJSONObject(i);
                final String thumb=data.getString("thumb");
                final String title=data.getString("title");
                final String time=data.getString("time");
                final int id=data.getInteger("id");
                final double price=data.getDouble("price");

                 final MultipleItemEntity entity=MultipleItemEntity.builder()
                         .setItemType(OrderListItemType.ITEM_ORDER_LIST)
                         .setField(MultipleFileds.ID,id)
                         .setField(MultipleFileds.IMAGE_URL,thumb)
                         .setField(MultipleFileds.TITLE,title)
                            .setField(OrderItemFields.PRICE,price)
                         .setField(OrderItemFields.TIME,time)
                         .build();
                 ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
