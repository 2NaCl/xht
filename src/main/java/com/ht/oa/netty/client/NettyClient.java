package com.ht.oa.netty.client;


import com.ht.oa.netty.protocol.protobuf.MessageBase;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class NettyClient {


    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 100; i > 0 ; i--) {
            arr[100-i] = arr[100-i] + 1;
            System.out.println(arr[100-i]);
        }

    }

    private EventLoopGroup worker = new NioEventLoopGroup();

    @Value("9090")
    private int port;

    @Value("127.0.0.1")
    private String host;

    private SocketChannel socketChannel;

    public void SendMsg(MessageBase.Message message) {
        socketChannel.writeAndFlush(message);
    }

    @PostConstruct
    public void start() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .remoteAddress(host, port)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new NettyClientInitializer());
        ChannelFuture future = bootstrap.connect();
        //客户端断线重连逻辑
        future.addListener((ChannelFutureListener) future1->{
            if (future1.isSuccess()) {
                log.info("连接Netty客户端成功");
            } else {
                log.info("连接失败，断线重连");
                future1.channel().eventLoop().schedule(
                        () -> start(), 20, TimeUnit.SECONDS
                );
            }
        });
        socketChannel = (SocketChannel) future.channel();
    }



}
