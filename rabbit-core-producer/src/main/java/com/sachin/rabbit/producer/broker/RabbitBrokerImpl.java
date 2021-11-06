package com.sachin.rabbit.producer.broker;

import com.sachin.rabbit.api.Message;
import com.sachin.rabbit.api.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitBrokerImpl implements RabbitBroker {

    @Autowired

    RabbitTemplateContainer rabbitTemplateContainer;

    @Override
    public void rapidSend(Message message) {
        message.setMessageType(MessageType.RAPID);
        sendKernel( message);
    }

    private void sendKernel(Message message) {


        AsyncQueue.submit(()->{
            String exchange = "exchange";
            String topic = message.getTopic();
            String routingKey = message.getRoutingKey();
            CorrelationData correlationData = new CorrelationData(String.format("%s#%s", message.getMessageId(), System.currentTimeMillis()));
            RabbitTemplate rabbitTemplate = rabbitTemplateContainer.getRabbitTemplate(message);

            rabbitTemplate.convertAndSend(topic,routingKey,message,correlationData);
            log.info("#RabbitBrokerImpl.sendKernel# send to rabbitmq, messageId:{}", message.getMessageId());
        });
    }

    @Override
    public void confirmSend(Message message) {

    }

    @Override
    public void reliantSend(Message message) {

    }
}
