package com.masternoy.rpi.server.protocol;

public interface Constants {
	
	interface Protocol{
		public static final byte SIGNATURE = (byte) 0x7e;
		public static final int MAX_LENGTH = 2048;
	}

}
