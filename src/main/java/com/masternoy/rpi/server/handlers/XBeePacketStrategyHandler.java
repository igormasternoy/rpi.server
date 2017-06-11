package com.masternoy.rpi.server.handlers;

import java.util.Set;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.masternoy.rpi.server.business.Strategy;
import com.masternoy.rpi.server.protocol.XBeePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class XBeePacketStrategyHandler extends SimpleChannelInboundHandler<XBeePacket> {
	private static final Logger log = Logger.getLogger(XBeePacketStrategyHandler.class);

	@Inject
	Set<Strategy> strategies;

	@Override
	public void channelRead0(ChannelHandlerContext ctx, XBeePacket msg) throws Exception {
		log.info("Serial port message received: " + msg);
		strategies.forEach(strat -> strat.process(msg));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.error("Exception caught", cause);
		super.exceptionCaught(ctx, cause);
	}

}
