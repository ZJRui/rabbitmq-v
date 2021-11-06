package com.sachin.rabbit.api;

import com.sachin.rabbit.api.exceptin.MessageRuntimException;

import java.util.List;

public interface MessageProducer {

    /**
     * 消息的发送，附带SendCallback回调
     * @param message
     * @param sendCallback
     */
    void send(Message message, SendCallback sendCallback);


    void send(Message message) throws MessageRuntimException;

    /**
     * 消息的批量发送
     * @param messages
     * @throws MessageRuntimException
     */
    void send(List<Message> messages) throws MessageRuntimException;

}
