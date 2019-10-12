package com.ht.oa.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import javax.print.DocFlavor;
import java.util.concurrent.TimeUnit;


@Slf4j
public class ServerStateHandler extends IdleStateHandler {

    /**
     * 设置空闲检测时间为30s
     */
    public static final int READER_TIME_OUT = 30;
    public ServerStateHandler() {
        super(READER_TIME_OUT,0,0,TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        log.info("{} 秒内没有读取到数据,关闭连接", READER_TIME_OUT);
    }
}
