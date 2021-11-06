package com.sachin.rabbit.common.serializer.impl;

import com.sachin.rabbit.common.serializer.Serializer;

public class JacksonSerializer implements Serializer {
    @Override
    public byte[] serializeRaw(Object data) {
        return new byte[0];
    }

    @Override
    public String serialize(Object data) {
        return null;
    }

    @Override
    public <T> T deserialize(String content) {
        return null;
    }

    @Override
    public <T> T deserialize(byte[] content) {
        return null;
    }
}
