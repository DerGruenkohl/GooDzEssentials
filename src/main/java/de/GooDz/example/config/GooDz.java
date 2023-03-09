package de.GooDz.example.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Checkbox;
import cc.polyfrost.oneconfig.config.annotations.Slider;
import cc.polyfrost.oneconfig.config.annotations.Text;
import cc.polyfrost.oneconfig.config.annotations.Number;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;

public class GooDz extends Config {
    @Checkbox(
            name = "AutoAccept",
            size = OptionSize.DUAL // optional
    )
    public static boolean AutoAccept = false;
    @Text(
            name = "Api Key",
            secure = true, multiline = false
    )
    public static String Key = "";
    @Slider(
            name = "delay",
            min = 2, max = 300
    )
    public static int warte = 4;
    @Checkbox(
            name = "mines",
            size = OptionSize.DUAL // optional
    )
    public static boolean mines = false;
    @Number(
            name = "Menge an Werbungslobbies",    // name of the component
            min = 1, max = 100,        // min and max values (anything above/below is set to the max/min
            step = 5        // each time the arrow is clicked it will increase/decrease by this amount
    )
    public static int werbungnum = 20; // default value
    @Checkbox(
            name = "dhub",
            size = OptionSize.DUAL // optional
    )
    public static boolean dhub = false;
    @Checkbox(
            name = "Spiders den",
            size = OptionSize.DUAL // optional
    )
    public static boolean Spider = false;
    public GooDz() {
        // Available mod types: PVP, HUD, UTIL_QOL, HYPIXEL, SKYBLOCK
        super(new Mod("GooDzMod", ModType.UTIL_QOL), "GooDz.json");
        initialize();

    }
}
