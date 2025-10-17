package com.theswcy_allay;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllayUtils implements ModInitializer {
	public static final String MOD_ID = "allay-utils";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {

		LOGGER.info("[ ALLAY UTILS ] Mod loaded!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
}