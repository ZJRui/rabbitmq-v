package com.sachin.rabbit.api.exceptin;

/**
 * @author Pekon
 */
public class MessageRuntimException extends RuntimeException {

    private static final long serialVersionUID = -7229344173872689350L;

    public MessageRuntimException() {
        super();
    }

    public MessageRuntimException(String message, Throwable throwable) {

        super(message, throwable);

    }
}
