package com.masternoy.rpi.server;

import com.digi.xbee.api.packet.common.RemoteATCommandPacket;
import com.google.inject.Singleton;

import io.netty.channel.ChannelFuture;
import io.netty.channel.rxtx.RxtxChannel;

@Singleton
public class ConnectionManager {
	
	RxtxChannel ctx;

	void setCtx(RxtxChannel ctx) {
		this.ctx = ctx;
	}
	
	RxtxChannel getCtx() {
		return ctx;
	}
	
	ChannelFuture writeToChannel(RemoteATCommandPacket command){
		return ctx.writeAndFlush(command);
	}

}
