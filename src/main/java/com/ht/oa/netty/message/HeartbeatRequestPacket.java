package com.ht.oa.netty.message;

public class HeartbeatRequestPacket extends Packet{
    @Override
    public Byte getCommed() {
        return 1;
    }
}
