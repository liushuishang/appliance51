package appliance51.push.service;

import appliance51.push.JPushConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuananyun on 2016/10/16.
 */
@Service
public class MessagePushService {

    private static final Logger LOG = LoggerFactory.getLogger(MessagePushService.class);
    @Autowired
    private JPushConfig pushConfig;

    public boolean push(Platform platform, Audience audience, Notification notification, Message message) {
        JPushClient jPushClient = pushConfig.getClient();

        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(platform)
                .setAudience(audience)
                .setNotification(notification)
                .setMessage(message)
                .setOptions(Options.newBuilder().setTimeToLive(pushConfig.getTimeToLive()).build())
                .build();
        try {
            PushResult result = jPushClient.sendPush(payload);
            LOG.info("Got result - " + result);
            return true;
        } catch (APIConnectionException e) {
            LOG.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            LOG.error("Should review the error, and fix the request", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
        return false;
    }


}
