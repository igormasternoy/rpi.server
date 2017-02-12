package com.masternoy.rpi.server;

import org.apache.log4j.Logger;

import com.masternoy.rpi.server.protocol.XBeeProtocolReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.rxtx.RxtxChannel;
import io.netty.channel.rxtx.RxtxDeviceAddress;
import io.netty.handler.codec.string.StringDecoder;

public class Startup {
	private final static String PORT = "/dev/cu.usbserial-A50285BI";
	private static final Logger log = Logger.getLogger(Startup.class);
	
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup group = new OioEventLoopGroup();
		log.info("Starting application");
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).
			channelFactory(new ChannelFactory()).//
			handler(new ChannelInitializer<RxtxChannel>() {
				@Override
				public void initChannel(RxtxChannel ch) throws Exception {
					ch.pipeline().addLast(//
							new XBeeProtocolReader(), //
							new StringDecoder(), //
							new SerialPortPacketHandler());
				}
			});

			ChannelFuture f = b.connect(new RxtxDeviceAddress(PORT)).sync();
			log.info("Connected to: "+PORT);
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully();
		}
	}

}
