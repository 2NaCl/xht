package com.ht.oa.netty.client;

import com.ht.oa.netty.protocol.protobuf.MessageBase;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyClientHandler extends SimpleChannelInboundHandler<MessageBase.Message> {


    /**
     * 如果服务端发生消息给客户端，下面的方法进行接收消息
     * @param channelHandlerContext
     * @param message
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageBase.Message message) throws Exception {
        log.info("客户端收到来自服务端的消息：{}", message.toString());
    }

    /**
     * 处理异常, 一般将实现异常处理逻辑的Handler放在ChannelPipeline的最后
     * 这样确保所有入站消息都总是被处理，
     * 无论它们发生在什么位置，下面只是简单的关闭Channel并打印异常信息
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
