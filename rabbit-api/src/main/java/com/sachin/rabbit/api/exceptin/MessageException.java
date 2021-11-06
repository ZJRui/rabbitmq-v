package com.sachin.rabbit.api.exceptin;

/**
 * @author Pekon
 */
public class MessageException extends Exception{
    private static final long serialVersionUID = -3185571492184050771L;

    public MessageException() {
        super();
    }

    public MessageException(String message, Throwable throwable) {

        super(message,throwable);

    }
}
