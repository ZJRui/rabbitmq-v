package com.sachin.rabbit.api;

import com.xiaoleilu.hutool.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Pekon
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Message implements Serializable {

    private static final long serialVersionUID = -8073405600727602510L;


    /**
     * 消息唯一ID
     */
    private String messageId = RandomUtil.randomString(8);

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


}
