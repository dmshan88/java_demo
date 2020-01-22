package com.example.util.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.util.AppPushUtil;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import lombok.extern.log4j.Log4j;

/** 极光推送 */
@Log4j
public class JPushUtilImpl implements AppPushUtil {

    private  String appKey = "";
    private String masterSecret = "";

    public JPushUtilImpl(String appKey, String masterSecret) {
        this.appKey = appKey;
        this.masterSecret = masterSecret;
    }
    
    @Override
    public Result pushAll(Message message) {
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.android(message.getContent(), message.getTitle(), message.getExtra())).build();
        return sendPayload(payload);
    }

    @Override
    public Result pushAliasOne(String alias, Message message) {
        List<String> aliasList = new ArrayList<>();
        aliasList.add(alias);
        return pushAliasList(aliasList, message);
    }

    @Override
    public Result pushAliasList(List<String> aliasList, Message message) {
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(aliasList))
                .setNotification(Notification.android(message.getContent(), message.getTitle(), message.getExtra())).build();
        return sendPayload(payload);
    }

    @Override
    public Result setAlias(String id, String alias) {
        Result result = new Result();
        result.setSuccess(false);
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
        try {
            jpushClient.updateDeviceTagAlias(id, alias, null, null);
            result.setSuccess(true);
            log.info("set alias " + id + " -> " + alias);
        } catch (APIConnectionException e) {
            log.error(e.getMessage());
            result.setError(e.getMessage());
        } catch (APIRequestException e) {
            log.error(e.getErrorMessage());
            result.setError(e.getErrorMessage());
        }
        return result;
    }

    /**发送消息*/
    private Result sendPayload(PushPayload payload) {
        Result result = new Result();
        result.setSuccess(false);
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
        try {
            jpushClient.sendPush(payload);
            result.setSuccess(true);
            Audience audicence = payload.getAudience();
            log.info("send payload to " + (audicence.isAll() ? "all" : audicence.toString()));
        } catch (APIConnectionException e) {
            log.error(e.getMessage());
            result.setError(e.getMessage());
        } catch (APIRequestException e) {
            log.error(e.getErrorMessage());
            result.setError(e.getErrorMessage());
        }
        return result;
    }

}
