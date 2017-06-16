package com.masternoy.rpi.server.business;

import com.digi.xbee.api.packet.XBeePacket;

public interface Strategy<T extends XBeePacket> {

	public void process(T packet);

}
