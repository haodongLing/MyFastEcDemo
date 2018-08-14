package haodong.com.fastecdemo.generators;

import haodong.com.latte.annotations.EntryGenerator;
import haodong.com.latte.annotations.PayEntryGenerator;
import haodong.com.latte_core.wechat.templates.WXPayEntryTemplate;

/**
 * Created by linghaoDo on 2018/8/2
 */
@PayEntryGenerator(
        pakegeName ="haodong.com.fastecdemo",
        payEntryTemplete = WXPayEntryTemplate.class

)
public interface WeChatPayEntry {
}
