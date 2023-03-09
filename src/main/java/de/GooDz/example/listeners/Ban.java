package de.GooDz.example.listeners;

import de.GooDz.example.command.kicklist;
import de.GooDz.example.config.GooDz;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Ban {
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) throws IOException {
        String message = event.message.getUnformattedText();
        if(message.contains("has requested to join")) {
            message = message.replace("-----------------------------------------------------\n", "").replace("\nClick here to accept or type /guild accept obvKillua!\n-----------------------------------------------------\n", "").replace("[VIP] ", "").replace("[VIP+] ", "").replace("[MVP] ", "").replace("[MVP+] ", "").replace("[MVP++] ", "");
            String[] messageSplit = message.split(" ");
            String player = messageSplit[0];
            if(ban(player)) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GRAY + "[" + EnumChatFormatting.BLUE + player + EnumChatFormatting.RED + " ist gebannt" + EnumChatFormatting.DARK_GRAY + "]"));
                System.out.println(player);
            }
            else {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GRAY + "[" + EnumChatFormatting.BLUE + player + EnumChatFormatting.GREEN + " ist nicht gebannt" + EnumChatFormatting.DARK_GRAY + "]"));
                System.out.println(player);
                if (GooDz.AutoAccept && !kicklist.getGuild().equalsIgnoreCase("GooDzAdvanced")){
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/guild accept "+ player);
                }
            }
        }
    }
    public boolean ban(String searchString) throws IOException {
        String url = "http://91.218.66.180:2702/api/bans";

        URLConnection connection = new URL(url).openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        JSONArray jsonArray = new JSONArray(stringBuilder.toString());
        String player1 = null;
        if(jsonArray.length() <= 10) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "ES GAB EINEN FEHLER BEIM CHECKEN BITTE MIT DEM BOT CHECKEN"));
            return true;
        }
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Es wurden " + jsonArray.length() + "gebannte spieler gefunden. Wenn diese Zahl nicht stimmen kann nicht annehmen und per bot checken"));
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            player1 = jsonObject.getString("username");
            if (player1.equalsIgnoreCase(searchString)) {
                System.out.println("BANNED");
                return true;
            }
        }
        System.out.println("NICHT BANNED");
        System.out.println(player1 + " + player 1");
        System.out.println(searchString + " + SearchString");
        return false;
    }
}
