package com.ht.oa.netty.client;

import com.ht.oa.netty.protocol.protobuf.MessageBase;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
@Slf4j
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {


    @Autowired
    private NettyClient nettyClient;

    /**
     * 当用户调用Channel.fireUserEventTriggered方法的时候触发，用户可以传递一个自定义的对象当这个方法里
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("get here");
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                log.info("已经10s没有发送消息给服务端");
                //向服务端送心跳包
                MessageBase.Message heartbeat = MessageBase.Message.newBuilder()
                        .setCmd(MessageBase.Message.CommandType.HEARTBEAT_REQUEST)
                        .setRequestId(UUID.randomUUID().toString())
                        .setContent("heartbeat").build();
                //发送心跳消息，并在发送失败时关闭该连接
                ctx.writeAndFlush(heartbeat).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    //channel未连接到远程服务器
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //如果运行过程中服务器挂了，就执行重连机制

        EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(
                () -> nettyClient.start(), 10L, TimeUnit.SECONDS
        );
        super.channelInactive(ctx);
    }



    /**
     * 处理Throwable通过登录和关闭Channel。
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("异常出现了：{}", cause.getMessage());
        ctx.channel().close();

    }
}
