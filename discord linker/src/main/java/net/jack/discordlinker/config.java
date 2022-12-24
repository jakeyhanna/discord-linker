package net.jack.discordlinker;


import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "discordlink")
class ModConfig implements ConfigData {
    String channelid = "";
    String writetoken = "";
    String onlinemessage = "";
    String status = "";
}
