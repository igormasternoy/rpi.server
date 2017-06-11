package com.masternoy.rpi.server.guice;

import com.masternoy.rpi.server.handlers.XBeeCommandResponseHandler;
import com.masternoy.rpi.server.handlers.XBeePacketStrategyHandler;

public interface SerialPortPacketHandlerFactory {

	XBeePacketStrategyHandler getPacketHandler();
	XBeeCommandResponseHandler getResponseHandler();
	

}
