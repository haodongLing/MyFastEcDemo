package haodong.com.latte.ec.main;

import android.graphics.Color;

import java.util.LinkedHashMap;

import haodong.com.latte.ec.main.cart.ShopCartDelegate;
import haodong.com.latte.ec.main.discover.DiscoverDelegate;
import haodong.com.latte.ec.main.index.IndexDelegate;
import haodong.com.latte.ec.main.personal.PersonalDelegate;
import haodong.com.latte.ec.main.sort.SortDelegate;
import haodong.com.latte_core.delegates.bottom.BaseBottomDelegate;
import haodong.com.latte_core.delegates.bottom.BottomItemDelegate;
import haodong.com.latte_core.delegates.bottom.BottomTabBean;
import haodong.com.latte_core.delegates.bottom.ItemBuilder;

/**
 * Created by linghaoDo on 2018/8/3
 */
public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder itemBuilder) {
        final LinkedHashMap<BottomTabBean,BottomItemDelegate>items=new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return itemBuilder.addItems(items).build();
    }

    /**
     * 默认
     * @return
     */
    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
