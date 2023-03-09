package de.GooDz.example.command;

import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import net.minecraft.client.Minecraft;

import static de.GooDz.example.config.GooDz.*;
import static java.lang.Thread.sleep;

@Command(value = "werbung", description = "macht werbung")
public class Werbung extends Thread{
    public void run(){
        for (int i = 0;  i <= werbungnum; i++){
            warp();
            System.out.println("Die Werbung is "+i + " mal durchgelaufen");
        }
    }
    @Main
    private void handle() {
        Werbung thread = new Werbung();
        thread.start();
    }
        public void warp(){
            try{
           if(dhub){
               Minecraft.getMinecraft().thePlayer.sendChatMessage("/warp dhub");
               sleep((long) (0.5*(warte* 1000L)));
               Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac Du suchst nach einer hilfsbereiten, freundlichen und deutschen Skyblock Gilde? Dann mach doch gerne mal /g join "+ kicklist.getGuild());
               sleep((long) (0.5*(warte* 1000L)));
               Minecraft.getMinecraft().thePlayer.sendChatMessage("/warp hub");
               sleep(warte* 1000L);
               System.out.println("Werbung dhub");
           }
           if(Spider){
               Minecraft.getMinecraft().thePlayer.sendChatMessage("/warp spider");
               sleep((long) (0.5*(warte* 1000L)));
               Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac Du suchst nach einer hilfsbereiten, freundlichen und deutschen Skyblock Gilde? Dann mach doch gerne mal /g join "+ kicklist.getGuild());
               sleep((long) (0.5*(warte* 1000L)));
               Minecraft.getMinecraft().thePlayer.sendChatMessage("/warp hub");
               sleep(warte* 1000L);
               System.out.println("Werbung Spider");
           }

           if(mines){
               Minecraft.getMinecraft().thePlayer.sendChatMessage("/warp mines");
               sleep((long) (0.5*(warte* 1000L)));
               Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac Du suchst nach einer hilfsbereiten, freundlichen und deutschen Skyblock Gilde? Dann mach doch gerne mal /g join "+ kicklist.getGuild());
               sleep((long) (0.5*(warte* 1000L)));
               Minecraft.getMinecraft().thePlayer.sendChatMessage("/warp hub");
               sleep(warte* 1000L);
               System.out.println("Werbung mines");
           }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
}
