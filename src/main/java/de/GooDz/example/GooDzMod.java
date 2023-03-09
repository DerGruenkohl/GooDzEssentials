package de.GooDz.example;

import de.GooDz.example.command.Werbung;
import de.GooDz.example.listeners.Ban;
import de.GooDz.example.command.gui;
import de.GooDz.example.command.kicklist;
import de.GooDz.example.config.GooDz;
import cc.polyfrost.oneconfig.events.event.InitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * The entrypoint of the Example Mod that initializes it.
 *
 * @see Mod
 * @see InitializationEvent
 */
@Mod(modid = GooDzMod.MODID, name = GooDzMod.NAME, version = GooDzMod.VERSION)
public class GooDzMod {
    public static final String MODID = "GooDzEssentials";
    public static final String NAME = "GooDzEssentials";
    public static final String VERSION = "1.0.0";
    // Sets the variables from `gradle.properties`. See the `blossom` config in `build.gradle.kts`.
    @Mod.Instance(MODID)
    public static GooDzMod INSTANCE; // Adds the instance of the mod, so we can access other variables.
    public static GooDz config;

    // Register the config and commands.
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new GooDz();
        CommandManager.INSTANCE.registerCommand(new gui());
        CommandManager.INSTANCE.registerCommand(new kicklist());
        CommandManager.INSTANCE.registerCommand(new Werbung());
        MinecraftForge.EVENT_BUS.register(new Ban());
    }
}
