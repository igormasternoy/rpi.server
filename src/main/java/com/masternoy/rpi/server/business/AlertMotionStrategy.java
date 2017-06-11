package com.masternoy.rpi.server.business;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.masternoy.rpi.server.DeviceCommandQueuer;
import com.masternoy.rpi.server.protocol.Constants;
import com.masternoy.rpi.server.protocol.XBeeCommandRequest;
import com.masternoy.rpi.server.protocol.XBeeCommandRequestListener;
import com.masternoy.rpi.server.protocol.XBeePacket;

/**
 * If motion sensor triggered switch sleeping mode to pin wake up; Switch back
 * when it finished.
 * 
 * @author imasternoy
 *
 */
@Singleton
public class AlertMotionStrategy implements Strategy {
	private static final int REPEAT_MESSAGE_TIMES = 3;

	private static final Logger log = Logger.getLogger(DeviceCommandQueuer.class);

	@Inject
	DeviceCommandQueuer commandQueuer;

	/**
	 * Currently for each packet send on/off DIO3
	 * 
	 * @param packet
	 */
	@Override
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

		XBeeCommandRequestListener listener = new XBeeCommandRequestListener(cmdReq) {
			int repeatCounter = 0;

			@Override
			public void onSuccess(XBeePacket packet) {
				log.debug("Received response for command" + cmdReq);
			}

			@Override
			public void onFailed(boolean wasEvicted) {
				if (wasEvicted) {
					log.warn("TIMEOUT waiting for response try Num" + repeatCounter + " command" + cmdReq);
					repeatCounter++;
					if (repeatCounter < REPEAT_MESSAGE_TIMES) {
						commandQueuer.put(this);
					}
				}
			}
		};
		commandQueuer.put(listener);

	}

}
