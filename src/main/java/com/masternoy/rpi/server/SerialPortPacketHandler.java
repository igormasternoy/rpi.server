package com.masternoy.rpi.server;

import org.apache.log4j.Logger;

import com.masternoy.rpi.server.protocol.XBeePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SerialPortPacketHandler extends SimpleChannelInboundHandler<XBeePacket> {
	private static final Logger log = Logger.getLogger(SerialPortPacketHandler.class);

	@Override
	public void channelRead0(ChannelHandlerContext ctx, XBeePacket msg) throws Exception {
		log.info("Serial port message: " + msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.error("Exception caught", cause);
		super.exceptionCaught(ctx, cause);
	}

}