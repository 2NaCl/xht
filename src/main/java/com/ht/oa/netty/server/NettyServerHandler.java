package com.ht.oa.netty.server;

import com.ht.oa.netty.message.HeartbeatResponsePacket;
import com.ht.oa.netty.protocol.protobuf.MessageBase;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.requests.HeartbeatResponse;
import sun.plugin2.message.HeartbeatMessage;

@Slf4j
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<MessageBase.Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageBase.Message message) throws Exception {

        if (message.getCmd().equals(MessageBase.Message.CommandType.HEARTBEAT_REQUEST)) {
            log.info("收到客户端发来的心跳消息：{}", message.toString());
            channelHandlerContext.writeAndFlush(new HeartbeatResponsePacket());
        }

    }
}
