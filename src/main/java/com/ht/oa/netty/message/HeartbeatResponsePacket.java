package com.ht.oa.netty.message;

public class HeartbeatResponsePacket extends Packet{
    @Override
    public Byte getCommed() {
        return 2;
    }

}
