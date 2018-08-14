package haodong.com.fastecdemo.generators;

import haodong.com.latte.annotations.EntryGenerator;
import haodong.com.latte_core.wechat.templates.WXEntryTemplate;

/**
 * Created by linghaoDo on 2018/8/2
 */
@EntryGenerator(
        pakegeName ="haodong.com.fastecdemo",
        EntryTemplete = WXEntryTemplate.class

)
public interface WeChatEntry {
}
