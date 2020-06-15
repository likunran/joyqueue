package com.jd.joyqueue.broker.jmq2.network.codec;

import com.google.common.collect.Lists;
import com.jd.joyqueue.broker.jmq2.JMQ2CommandType;
import com.jd.joyqueue.broker.jmq2.command.RetryMessage;
import com.jd.joyqueue.broker.jmq2.network.JMQ2PayloadCodec;
import com.jd.joyqueue.broker.jmq2.util.Serializer;
import org.joyqueue.message.MessageLocation;
import org.joyqueue.network.session.ConsumerId;
import org.joyqueue.network.transport.command.Header;
import org.joyqueue.network.transport.command.Payload;
import org.joyqueue.network.transport.command.Type;
import io.netty.buffer.ByteBuf;

import java.util.Collections;
import java.util.List;

/**
 * retryMessage
 * author: gaohaoxiang
 * email: gaohaoxiang@jd.com
 * date: 2018/8/21
 */
public class RetryMessageCodec implements JMQ2PayloadCodec, Type {

    @Override
    public Object decode(Header header, ByteBuf buffer) throws Exception {
        RetryMessage payload = new RetryMessage();
        payload.setConsumerId(new ConsumerId(Serializer.readString(buffer, 1)));
        payload.setException(Serializer.readString(buffer, 2));
        payload.setLocations(readMessageLocations(buffer));
        return payload;
    }

    @Override
    public void encode(Payload payload, ByteBuf buffer) {

    }

    /**
     * 解码应答消息体中的消息位置数组
     *
     * @param buffer 输入缓冲区
     * @return 消息位置数组
     * @throws java.lang.Exception
     * io.netty.buffer.ByteBuf)
     */
    public static List<MessageLocation> readMessageLocations(final ByteBuf buffer) throws Exception {
        if (buffer == null) {
            return Collections.emptyList();
        }
        // 6字节服务地址
        byte[] address = Serializer.readBytes(buffer, 6);
        // 1字节主题长度
        String topic = Serializer.readString(buffer, 1);

        // 2字节条数
        int length = buffer.readUnsignedShort();

        List<MessageLocation> locations = Lists.newArrayListWithCapacity(length);
        short queueId;
        long queueOffset;
        for (int i = 0; i < length; i++) {
            // 1字节队列ID
            queueId = buffer.readUnsignedByte();
            // 8字节队列偏移
            queueOffset = buffer.readLong();

            locations.add(new MessageLocation(topic, queueId, queueOffset));
        }

        return locations;
    }

    @Override
    public int type() {
        return JMQ2CommandType.RETRY_MESSAGE.getCode();
    }
}