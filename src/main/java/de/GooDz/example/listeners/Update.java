package de.GooDz.example.listeners;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;

public class Update {
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        if(message.contains("Welcome to Hypixel SkyBlock!")) {
            checkMod();
        }
    }
    public void checkMod(){

    }
}
