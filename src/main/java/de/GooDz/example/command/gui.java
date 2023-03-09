package de.GooDz.example.command;

import de.GooDz.example.GooDzMod;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;

/**
 * An example command implementing the Command api of OneConfig.
 * Registered in GooDzMod.java with `CommandManager.INSTANCE.registerCommand(new ExampleCommand());`
 *
 * @see Command
 * @see Main
 * @see GooDzMod
 */
@Command(value = "GooDz", description = "Access the " + GooDzMod.NAME + " GUI.")
public class gui {
    @Main
    private void handle() {
        GooDzMod.config.openGui();
    }
}