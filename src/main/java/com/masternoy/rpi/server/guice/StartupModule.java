package com.masternoy.rpi.server.guice;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import com.masternoy.rpi.server.business.AlertMotionStrategy;
import com.masternoy.rpi.server.business.Strategy;
import com.masternoy.rpi.server.handlers.XBeePacketStrategyHandler;

public class StartupModule extends AbstractModule {

	@Override
	protected void configure() {
		Multibinder<Strategy> strategies = Multibinder.newSetBinder(binder(), Strategy.class);
		strategies.addBinding().to(AlertMotionStrategy.class);
		install(new FactoryModuleBuilder().implement(XBeePacketStrategyHandler.class, XBeePacketStrategyHandler.class)
				.build(SerialPortPacketHandlerFactory.class));
	}

}
