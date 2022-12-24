package net.jack.discordlinker;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;

import static net.jack.discordlinker.Discordlinker.Channelid;

public class listner extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        if(event.getAuthor().isBot() || event.isWebhookMessage()) return;
        String message = event.getMessage().getContentRaw().toString();
        String name = event.getAuthor().getName();
        MinecraftClient mc = MinecraftClient.getInstance();
        TextChannel id = net.jack.discordlinker.Discordlinker.bot.getTextChannelById(Channelid);
        if (event.getChannel() == id){
            assert mc.player != null;
            mc.inGameHud.addChatMessage(MessageType.SYSTEM, Text.of(name + ": " + message),mc.player.getUuid());
        }
    }
}
