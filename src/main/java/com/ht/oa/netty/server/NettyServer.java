package com.ht.oa.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.net.InetSocketAddress;

@Component
@Slf4j
public class NettyServer {

    private EventLoopGroup bossGroup = new NioEventLoopGroup();//进行连接
    private EventLoopGroup workerGroup = new NioEventLoopGroup();//业务操作

    @Value("${netty.port}")
    private Integer port;

    @PostConstruct
    public void start() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                //指定channel
                .channel(NioServerSocketChannel.class)
                .childHandler(new NettyServerInitializer())
                //设置TCP长连接,一般如果两个小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //将小的数据包包装成更大的帧进行传送，提高网络的负载
                .childOption(ChannelOption.TCP_NODELAY, true)
                //服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                //使用指定的端口设置套接字地址
                .localAddress(new InetSocketAddress(port));
        ChannelFuture sync = serverBootstrap.bind().sync();
        if (sync.isSuccess()) {
            log.info("开始启动 Netty Server");
        }
    }

    @PreDestroy
    public void destroy() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        log.info("Netty已关闭");
    }





}
