package com.example.util;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**app推送服务*/
public interface AppPushUtil {
    
    /**返回结果*/
    @Getter
    @Setter
    public static class Result {
        private boolean success; //成功
        private String error; //错误信息
    }
    
    /**消息*/
    @Getter
    @Setter
    public static class Message {
        private String title; //标题
        private String content; //内容
        private Map<String, String> extra; //扩展字段
    }
    
    /**对所有设备推送*/
    Result pushAll(Message message);
    
    /**对单一别名推送*/
    Result pushAliasOne(String alias, Message message);
    
    /**对多别名推送*/
    Result pushAliasList(List<String> aliasList, Message message);
    
    /**设置设备别名*/
    Result setAlias(String id, String alias);

}
