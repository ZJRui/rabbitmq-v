package com.sachin.rabbit.api;

import java.util.HashMap;
import java.util.Map;

public class MessageBuilderTest {


    /**
     * 消息唯一ID
     */
    private String messageId;

    /**
     * Exchange类型定义为topic类型，消息的主题
     */
    private String topic;
    /**
     * 消息的路由规则
     */
    private String routingKey = "";

    private Map<String, Object> attributes = new HashMap<>();

    /**
     * 消息的延迟时间
     */
    private int delayMillis;

    /**
     * 消息的类型
     */
    private String messageType = MessageType.CONFIRM;


    private MessageBuilderTest() {

    }
    public static MessageBuilderTest create(){
        return new MessageBuilderTest();
    }
    public MessageBuilderTest withMessageId(String  messageId){
        this.messageId = messageId;
        return this;
    }

    public Message build() {
        return new Message(this.messageId, this.topic, this.routingKey, this.attributes, this.delayMillis, this.messageType);
    }

}
