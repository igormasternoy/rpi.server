package com.masternoy.rpi.server.guice;

import com.masternoy.rpi.server.SerialPortPacketHandler;

public interface SerialPortPacketHandlerFactory {

	SerialPortPacketHandler getPacketHandler();

}
