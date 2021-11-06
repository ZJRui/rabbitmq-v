package com.sachin.rabbit.producer.broker;


import com.google.common.base.Preconditions;
import com.sachin.rabbit.api.Message;
import com.sachin.rabbit.api.MessageProducer;
import com.sachin.rabbit.api.MessageType;
import com.sachin.rabbit.api.SendCallback;
import com.sachin.rabbit.api.exceptin.MessageRuntimException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Pekon
 */
@Component
public class ProducerClient  implements MessageProducer {



    @Override
    public void send(Message message) throws MessageRuntimException {

        Preconditions.checkNotNull(message.getTopic());


        String messageType = message.getMessageType();
        switch (messageType) {
            case MessageType.RAPID:

                break;
            case MessageType.CONFIRM:
                break;
            case MessageType.RELIANT:
                break;

            default:
                break;

        }

    }



    @Override
    public void send(Message message, SendCallback sendCallback) {

    }

    @Override
    public void send(List<Message> messages) throws MessageRuntimException {

    }
}
