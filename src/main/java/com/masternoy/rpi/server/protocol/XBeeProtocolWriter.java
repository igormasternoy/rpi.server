package com.masternoy.rpi.server.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import static com.masternoy.rpi.server.protocol.Constants.Protocol.*;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

/**
 * Writing messages @see XBeeCommandRequest back to serial port
 * 
 * 
 * @author imasternoy
 *
 */
public class XBeeProtocolWriter extends MessageToByteEncoder<XBeeCommandRequest> {
	private static final Logger log = Logger.getLogger(XBeeProtocolWriter.class);

	@Override
	protected void encode(ChannelHandlerContext ctx, XBeeCommandRequest msg, ByteBuf out) throws Exception {
		out.writeByte(SIGNATURE);
		ByteBuf byteBuf = Unpooled.buffer(16);// 16 Is default
		byteBuf.writeByte(msg.getFrameType());
		byteBuf.writeByte(msg.getFrameId());
		byteBuf.writeBytes(msg.getDestAddress());
		byteBuf.writeBytes(msg.getDestNetworkAddress());
		byteBuf.writeByte(msg.getRemoteCmdOpt());
		byteBuf.writeByte(msg.getAtCmdName()[0]);
		byteBuf.writeByte(msg.getAtCmdName()[1]);
		byteBuf.writeByte(msg.getCmdParam());
		byte[] bytes = new byte[byteBuf.writerIndex()];
		byteBuf.readBytes(bytes);
		out.writeShort(bytes.length);
		out.writeBytes(bytes);
		out.writeByte(XBeeCommandRequest.getCheckSum(bytes));
		
		log.debug("Message send with payload : " + msg.toString());
	}

}
