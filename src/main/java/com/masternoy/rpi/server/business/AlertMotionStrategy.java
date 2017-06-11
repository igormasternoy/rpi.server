package com.masternoy.rpi.server.business;

import java.nio.ByteBuffer;
import java.util.Arrays;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.masternoy.rpi.server.ConnectionManager;
import com.masternoy.rpi.server.protocol.Constants;
import com.masternoy.rpi.server.protocol.XBeeCommandRequest;
import com.masternoy.rpi.server.protocol.XBeePacket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * If motion sensor triggered switch sleeping mode to pin wake up; Switch back
 * when it finished.
 * 
 * @author imasternoy
 *
 */
@Singleton
public class AlertMotionStrategy {
	@Inject
	ConnectionManager connectionManager;

	/**
	 * Currently for each packet send on/off DIO3
	 * 
	 * @param packet
	 */
	public void process(XBeePacket packet) {
		boolean isOn = (packet.getDigitalSampleData() & Constants.PinSamplesMask.DIO3) == Constants.PinSamplesMask.DIO3;
		XBeeCommandRequest cmdReq = new XBeeCommandRequest();
		cmdReq.setFrameType((byte) 0x17);
		cmdReq.setFrameId((byte) 0x00);
		//
		cmdReq.setRemoteCmdOpt((byte) 0x02);
		//
		cmdReq.setAtCmdName(new byte[] { (byte) 0x44, (byte) 0x33 });
		if (isOn) {
			cmdReq.setCmdParam((byte) 0x04);
		} else {
			cmdReq.setCmdParam((byte) 0x05);
		}
		// TODO use message queue?
		connectionManager.writeToChannel(cmdReq);
	}

}
