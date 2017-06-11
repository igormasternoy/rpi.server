package com.masternoy.rpi.server;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.masternoy.rpi.server.protocol.XBeeCommandRequestListener;
import com.masternoy.rpi.server.protocol.XBeePacket;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

@Singleton
public class DeviceCommandQueuer {
	private static final Logger log = Logger.getLogger(DeviceCommandQueuer.class);

	@Inject
	ConnectionManager connectionManager;

	private Cache<Byte, XBeeCommandRequestListener> commandListeners;

	public DeviceCommandQueuer() {
		commandListeners = CacheBuilder.newBuilder() //
				.expireAfterWrite(10, TimeUnit.SECONDS) //
				.refreshAfterWrite(10, TimeUnit.SECONDS) //
				.removalListener(new RemovalListener<Byte, XBeeCommandRequestListener>() {
					@Override
					public void onRemoval(RemovalNotification<Byte, XBeeCommandRequestListener> notification) {
						notification.getValue().onFailed(notification.wasEvicted());
					}
				}).build();
	}

	public void put(final XBeeCommandRequestListener commandListener) {
		Byte frameId = commandListener.getRequest().getFrameId();
		commandListeners.put(frameId, commandListener);
		connectionManager.writeToChannel(commandListener.getRequest()).addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if (!future.isSuccess()) {
					log.error("FAILED to send command " + commandListener.getRequest() + " to the device  " + frameId);
				}
			}
		});
	}

	public void notifyCommandResponse(XBeePacket packet) {
		XBeeCommandRequestListener listener = commandListeners.getIfPresent(packet.getFrameId());
		if (listener != null) {
			listener.onSuccess(packet);
			commandListeners.invalidate(packet.getFrameId());
		} else {
			log.warn("WARNING received response, but no listeners found for packet: " + packet);
		}
	}

}
