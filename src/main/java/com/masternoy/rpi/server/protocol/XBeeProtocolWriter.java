package com.masternoy.rpi.server.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import static com.masternoy.rpi.server.protocol.Constants.Protocol.*;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

/**
 * Writing messages @see XBeeCommandRequests back to serial port
 * 
 * @author imasternoy
 *
 */
public class XBeeProtocolWriter extends MessageToByteEncoder<XBeeCommandRequest> {
	private static final Logger log = Logger.getLogger(XBeeProtocolWriter.class);

	@Override
	protected void encode(ChannelHandlerContext ctx, XBeeCommandRequest msg, ByteBuf out) throws Exception {
		out.writeByte(SIGNATURE);
		byte[] bytes = new byte[msg.getPayload().writerIndex()];
		msg.getPayload().readBytes(bytes);
		out.writeShort(bytes.length);
		out.writeBytes(bytes);
		out.writeByte(XBeeCommandRequest.getCheckSum(bytes));
		
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b).toUpperCase());
		}
		log.debug("Message send with payload : " + sb.toString());
	}

}
