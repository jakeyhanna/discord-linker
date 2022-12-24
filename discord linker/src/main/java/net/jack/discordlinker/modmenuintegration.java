package net.jack.discordlinker;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;

public class modmenuintegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?>
    getModConfigScreenFactory(){
        return parent ->
                AutoConfig.getConfigScreen(ModConfig.class,parent).get();
    }
}


