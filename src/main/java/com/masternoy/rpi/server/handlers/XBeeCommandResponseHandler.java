package com.masternoy.rpi.server.handlers;

import com.google.inject.Inject;
import com.masternoy.rpi.server.DeviceCommandQueuer;
import com.masternoy.rpi.server.protocol.XBeePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Handler responsible to notify listeners callback on CommandResponse
 * 
 * @author imasternoy
 *
 */
public class XBeeCommandResponseHandler extends SimpleChannelInboundHandler<XBeePacket> {
	@Inject
	DeviceCommandQueuer queuer;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, XBeePacket msg) throws Exception {
		if (msg.getFrameId() != -1) {
			queuer.notifyCommandResponse(msg);
		}
	}

}
