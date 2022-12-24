package net.jack.discordlinker;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.util.mod.Mod;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.File;


public class Discordlinker implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	public static JDA bot;
	public static String Channelid;
	public static String onlinemessage;
	public static File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "discordlink.properties");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);

		ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();


		@Environment(EnvType.CLIENT)
		class ModMenuIntegration implements ModMenuApi{
			@Override
			public ConfigScreenFactory<?>
			getModConfigScreenFactory(){
					return parent ->
							AutoConfig.getConfigScreen(ModConfig.class,parent).get();
				}
			}


		UpdateConfig();



		try {
			if (config.writetoken != "") {
				bot = JDABuilder.createDefault(config.writetoken).addEventListeners(new listner()).build();
			}
		} catch (LoginException e) {
			e.printStackTrace();
		}



		if (config.writetoken != "") {
			if (config.status != "") {
				bot.getPresence().setActivity(Activity.playing(config.status));
			}
		}

		LOGGER.info("Hello Fabric world!");
	}


public static void UpdateConfig() {
	ModConfig config1 = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
	AutoConfig.getConfigHolder(ModConfig.class).getConfig();
	Discordlinker.Channelid = config1.channelid;
	Discordlinker.onlinemessage = config1.onlinemessage;
	}
}