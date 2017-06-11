package com.masternoy.rpi.server.business;

import com.masternoy.rpi.server.protocol.XBeePacket;

public interface Strategy {

	public void process(XBeePacket packet);

}
