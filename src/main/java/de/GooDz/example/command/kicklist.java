package de.GooDz.example.command;

import de.GooDz.example.GooDzMod;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import de.GooDz.example.util.DiscordWebhook;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.apache.commons.io.IOUtils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static de.GooDz.example.config.GooDz.Key;

/**
 * An example command implementing the Command api of OneConfig.
 * Registered in GooDzMod.java with `CommandManager.INSTANCE.registerCommand(new ExampleCommand());`
 *
 * @see Command
 * @see Main
 * @see GooDzMod
 */
@Command(value = "kicklist", description = "kicklist ")
public class kicklist {
    public String[] UUID;
    public String[] Name;
    @Main
    private void handle() {
        try {
            getKicklist(getGuild());
            DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/1083509454613520506/RGfp9VGBN6BO63VtMNOU62hVbJS0xhXpEVbW9AaE6uPYLclzuJW29jz2puP6dLH-X4n0");
            webhook.setAvatarUrl("https://cdn.discordapp.com/attachments/702666220830326904/1043942953820893254/c588639778429438ed666cc704a35a33.png");
            webhook.setUsername("GooDzMod");
            webhook.setTts(false);
            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setTitle("GooDzLogging")
                    .setDescription(Minecraft.getMinecraft().thePlayer.getName() + " Hat /kicklist benutzt")
                    .setColor(Color.RED));
            webhook.execute(); //Handle exception
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void getKicklist(String guild) throws IOException {
        String url = "http://91.218.66.180:2702/api/kicks/"+guild;
        getUUIDs(url);
    }
    public void getUUIDs(String url) throws IOException {

        URLConnection connection = new URL(url).openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        JSONArray jsonArray = new JSONArray(stringBuilder.toString());
        List<String> uuidList = new ArrayList<>();
        List<String> uuidList2 = new ArrayList<>();
        jsonArray.forEach(item -> {
            JSONObject jsonObject = (JSONObject) item;
            if (jsonObject.getInt("gxp") == 0){
                String uuid = jsonObject.getString("uuid");
            uuidList.add(uuid);
            try {
                String name = getMinecraftUsername(uuid);
                uuidList2.add(name);
                if(uuidList2.toArray().length == 0){
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN+ " Niemand muss gekickt werden"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        });

        UUID = uuidList.toArray(new String[0]);
        Name = uuidList2.toArray(new String[0]);
    }
    public String getMinecraftUsername(String uuid) throws IOException {
            URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/"+uuid);
            InputStream input = url.openConnection().getInputStream();
            String jsonText = IOUtils.toString(input, "UTF-8");
            JSONObject json = new JSONObject(jsonText);
            String name = json.getString("name");
            System.out.println(name);
         Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + name+ EnumChatFormatting.RED + " muss gekickt werden"));
         return name;
    }
    public static String getGuild() throws IOException {
        String id = String.valueOf(Minecraft.getMinecraft().thePlayer.getUniqueID());
        String urlString = "https://api.hypixel.net/guild?key="+Key+"&player="+id;
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();

        JSONObject json = new JSONObject(stringBuilder.toString());
        if (json.getBoolean("success")) {
            JSONObject guild = json.getJSONObject("guild");
            return guild.getString("name");
        } else {
            return "API request failed: " + json.getString("cause");
        }
    }

}
