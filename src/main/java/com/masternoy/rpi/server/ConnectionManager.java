package com.masternoy.rpi.server;

import com.digi.xbee.api.packet.common.RemoteATCommandPacket;
import com.google.inject.Singleton;

import io.netty.channel.ChannelFuture;
import io.netty.channel.rxtx.RxtxChannel;

@Singleton
public class ConnectionManager {
	
	RxtxChannel xBeeCtx;
	RxtxChannel gsmCtx;
	
	public void setGsmCtx(RxtxChannel ctx){
		this.gsmCtx = ctx;
	}

	public void setXBeeCtx(RxtxChannel ctx) {
		this.xBeeCtx = ctx;
	}
	
	public RxtxChannel getXBeeCtx() {
		return xBeeCtx;
	}
	
	public RxtxChannel getGsmCtx() {
		return gsmCtx;
	}
	
	public ChannelFuture writeToGSMChannel(Object command){
		return gsmCtx.writeAndFlush(command);
	}
	
	public ChannelFuture writeToXBeeChannel(RemoteATCommandPacket command){
		return xBeeCtx.writeAndFlush(command);
	}

}
