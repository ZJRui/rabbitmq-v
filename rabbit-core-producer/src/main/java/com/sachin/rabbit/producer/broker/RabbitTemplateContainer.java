package com.sachin.rabbit.producer.broker;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.sachin.rabbit.api.Message;
import com.sachin.rabbit.api.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 池化RabbitTemplate
 *
 *
 * 1，每一个Topic对应一个RabbitTemplate
 *
 *
 * 关于RabbitTemplate的线程安全性：
 * 为什么rabbitTemplate有时候要设置原型模式：没有必须是prototype类型，rabbitTemplate是thread safe的，
 * 主要是channel不能共用，但是在rabbitTemplate源码里channel是threadlocal的，所以singleton没问题。但是rabbitTemplate要设置回调类，如果是singleton，回调类就只能有一个，所以如果想要设置不同的回调类，就要设置为prototype的scope。
 *
 */
@Component
@Slf4j
public class RabbitTemplateContainer  implements  RabbitTemplate.ConfirmCallback {


    private Map<String, RabbitTemplate> rabbitTemplateMap = Maps.newConcurrentMap();

    private  Splitter splitter=Splitter.on("#").omitEmptyStrings().trimResults();


    //RabbitTemplate 需要连接工厂
    @Autowired
    private ConnectionFactory connectionFactory;

    public RabbitTemplate getRabbitTemplate(Message message) {

        Preconditions.checkNotNull(message.getTopic());
        String topic = message.getTopic();
        RabbitTemplate rabbitTemplate = rabbitTemplateMap.get(topic);
        if (rabbitTemplate != null) {
            return rabbitTemplate;
        }
        log.info("Topic{} is not existRabbitTemplate", message);
        RabbitTemplate rabbitTemplateNew=new RabbitTemplate(connectionFactory);
        rabbitTemplateNew.setExchange(message.getTopic());
        rabbitTemplateNew.setRetryTemplate(new RetryTemplate());
        rabbitTemplateNew.setRoutingKey(message.getRoutingKey());
        //对于Message的序列化方式
        //rabbitTemplateNew.setMessageConverter();

        String messageType = message.getMessageType();
        //只有迅速消息才不需要confirm，confirm消息和可靠性消息relaint都需要confirm
        if (!StringUtils.endsWithIgnoreCase(messageType, MessageType.RAPID)) {
            //非迅速消息设置callback
            rabbitTemplateNew.setConfirmCallback(this);

        }
        rabbitTemplateMap.putIfAbsent(topic, rabbitTemplateNew);

        return rabbitTemplateNew;

    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        //用于消息确认

        List<String> datas = splitter.splitToList(correlationData.getId());
        String messageId = datas.get(0);
        Long sendTime = Long.parseLong(datas.get(1));
        if (b) {
            log.info("send message is ok ,confirm messageid:{},sendTime{}", messageId, sendTime);
        }else{
            log.error("send message error ,messageId:{},sendTime:{},errorMessage:{}", messageId, sendTime, s);
        }
    }
}
