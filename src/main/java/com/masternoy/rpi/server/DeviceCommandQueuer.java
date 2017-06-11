package com.masternoy.rpi.server;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import com.google.inject.Singleton;
import com.masternoy.rpi.server.protocol.XBeeCommandRequest;

@Singleton
public class DeviceCommandQueuer {
	private ConcurrentHashMap<Long, Queue<XBeeCommandRequest>> commandToDevice;

	public DeviceCommandQueuer() {
		commandToDevice = new ConcurrentHashMap<Long, Queue<XBeeCommandRequest>>();
	}

	public void put(Long deviceMac, final XBeeCommandRequest command) {
		commandToDevice.computeIfPresent(deviceMac, (mac, queue) -> {
			queue.add(command);
			return queue;
		});

		commandToDevice.computeIfAbsent(deviceMac, a -> {
			LinkedList<XBeeCommandRequest> queue = new LinkedList<XBeeCommandRequest>();
			queue.push(command);
			return queue;
		});
	}

	public Optional<Queue<XBeeCommandRequest>> getCommandsForDevice(Long mac) {
		return Optional.of(commandToDevice.get(mac));
	}
}
