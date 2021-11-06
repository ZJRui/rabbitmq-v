package com.sachin.rabbit.producer.broker;

import com.sachin.rabbit.api.Message;

/**
 * 具体发送不同类型消息的接口
 */
public interface RabbitBroker {

    void rapidSend(Message message);

    void confirmSend(Message message);

    void reliantSend(Message message);
}
