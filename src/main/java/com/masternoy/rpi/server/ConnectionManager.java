package com.masternoy.rpi.server;

import com.google.inject.Singleton;
import com.masternoy.rpi.server.protocol.XBeeCommandRequest;

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
	
	ChannelFuture writeToChannel(XBeeCommandRequest command){
		return ctx.writeAndFlush(command);
	}

}
