package com.masternoy.rpi.server.protocol;

public interface Constants {
	public static final byte HIGH = 5;
	public static final byte LOW = 4;
	
	interface PinSamplesMask {
		public static final short DIO1 = 0b0000000000000010;
		public static final short DIO2 = 0b0000000000000100;
		public static final short DIO3 = 0b0000000000001000;
		public static final short DIO4 = 0b0000000000010000;
	}
	
	interface Protocol{
		public static final byte SIGNATURE = (byte) 0x7e;
		public static final int MAX_LENGTH = 2048;
	}

}
