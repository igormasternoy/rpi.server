package com.masternoy.rpi.server.trash;

import static com.masternoy.rpi.server.protocol.Constants.Protocol.SIGNATURE;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import com.masternoy.rpi.server.protocol.XBeeCommandRequest;
import com.masternoy.rpi.server.protocol.XBeePacket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException, DecoderException {
		byte[] b = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,(byte) 0xFF,(byte) 0xFF};
		ByteBuf buf = Unpooled.wrappedBuffer(b);
		long l = buf.readLong();
		ByteBuf out = Unpooled.buffer(8);
		out.writeLong(l);
		
		System.out.print("Byte array: ");
		for (byte c : out.array()) {
			System.out.print(String.format("%02x", c).toUpperCase());
		}
		System.out.println(" Long value: "+l);
		
//		XBeeCommandRequest cmdReq = process(null);
//		byte[] bts = new byte[payload.writerIndex()];
//		payload.readBytes(bts);
//		ByteBuf out = Unpooled.buffer(18,20);
//		out.writeByte(SIGNATURE);
//		out.writeShort(bts.length);
//		out.writeBytes(bts);
//		out.writeByte(cmdReq.getCheckSum(bts));
//		
//		
//		for (byte b : out.array()) {
//			System.out.print(String.format("%02x", b).toUpperCase());
//		}

		// System.out.println(sb.toString());
		// System.out.println(Integer.toHexString( (0xFF - ((0x17 + 0xFF + 0xFF
		// + 0xFF + 0xFE + 0x02 + 0x44 + 0x33 +0x05) & 0xFF))));
		// ByteBuf byteBuf = Unpooled.buffer();
		// byteBuf.writeByte(0x17);
		// byteBuf.writeByte(0xFF);
		// byteBuf.writeByte(0xFF);
		// byteBuf.writeByte(0xFF);
		// byteBuf.writeByte(0xFE);
		// byteBuf.writeByte(0x02);
		// byteBuf.writeByte(0x44);
		// byteBuf.writeByte(0x33);
		// byteBuf.writeByte(0x05);
		//
		// short sum = 0;
		// for (byte bytes : byteBuf.array()) {
		// sum += bytes;
		// }
		// byte checkSum = (byte) (0xFF - (sum & 0xFF));
		//
		// System.out.println(Integer.toHexString( checkSum));
		//

	}

	public static long bytesToLong(byte[] by) {
		long value = 0;
		for (int i = 0; i < by.length; i++) {
			value += ((long) by[i] & 0xffL) << (8 * i);
		}
		return value;
	}

	public static XBeeCommandRequest process(XBeePacket packet) {
		XBeeCommandRequest cmdReq = new XBeeCommandRequest();
		cmdReq.setFrameType((byte) 0x17);
		cmdReq.setFrameId((byte) 0x00);
		//
		cmdReq.setRemoteCmdOpt((byte) 0x02);
		//
		cmdReq.setAtCmdName(new byte[] { (byte) 0x44, (byte) 0x33 });
		if (false) {
			cmdReq.setCmdParam((byte) 0x04);
		} else {
			cmdReq.setCmdParam((byte) 0x05);
		}
		return cmdReq;
	}

}
