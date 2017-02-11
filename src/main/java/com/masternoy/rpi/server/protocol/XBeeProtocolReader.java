package com.masternoy.rpi.server.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.apache.commons.codec.binary.Hex;

/**
 * @author imasternoy
 */
public class XBeeProtocolReader {
	public static final byte SIGNATURE = (byte) 0x7e;

	ByteBuffer payload;
	InputStream inputStream;
	State currentState;

	enum State {
		START, END
	}

	public XBeeProtocolReader(InputStream in) {
		payload = ByteBuffer.allocate(100);
		inputStream = in;
		currentState = State.START;
	}

	public XBeePacket getPacket() {
		return null;
	}

	public XBeePacket readNextPacket(int lenght) {
		if (currentState == State.START) {
			byte[] available = new byte[lenght];
//			inputStream.read(available, 0, lenght);
		}
		return null;
	}

}
