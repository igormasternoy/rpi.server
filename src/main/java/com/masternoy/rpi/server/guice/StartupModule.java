package com.masternoy.rpi.server.guice;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.masternoy.rpi.server.SerialPortPacketHandler;

public class StartupModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(SerialPortPacketHandler.class, SerialPortPacketHandler.class)
				.build(SerialPortPacketHandlerFactory.class));
	}

}
