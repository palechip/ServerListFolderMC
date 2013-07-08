// part of the Server List Folder mod by palechip
// license GPLv3 (http://www.gnu.org/licenses/quick-guide-gplv3.html)
// to extend the mod, you need to decompile Minecraft and copy the modifyed code to the given function.
// that's because it isn't allowed to release decompiled minecraft code.

// config originally written by lbjdaking23 for https://github.com/UnofficalProjectAresTeam/mod_Ares/

package net.minecraft.src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import net.minecraft.src.Minecraft;

public class ServerListFolderConfig {
    private static Properties defaults = new Properties();
    private String configPath;
    private Properties config;
    
    // update this value to change the config version.
    private static int version = 1;

    // main variables
    public static int configVersion;
    public static EnumChatFormatting FolderMotdColorCode;
    private static String FolderMotdColor;
    public static String FolderMotdText;

    /**
     * Default values created when class is first referenced
     */
    static {
        // if the value is missing, it should force an update. Don't change it.
        defaults.setProperty("configVersion", "0");
        defaults.setProperty("FolderMotdColorCode", "DARK_PURPLE");
        defaults.setProperty("FolderMotdColor", "purple");
        defaults.setProperty("FolderMotdText", "Folder");
    }

    public ServerListFolderConfig() {
        ServerListFolder.info("Attempting to load/create the configuration.");
        loadConfig();
        loadConfigData();
    }

    /**
     * Attempts to find a config
     * If there is one load it
     * If there is not one create one
     */
    private void loadConfig() {
        config = new Properties(defaults);

        try {
            configPath = Minecraft.getMinecraft().mcDataDir.getCanonicalPath() + File.separatorChar + "config" + File.separatorChar + "ServerListFolder" + File.separatorChar;

            File cfg = new File(configPath + "ServerListFolder.cfg");

            if(cfg.exists()) {
                ServerListFolder.info("Config file found, loading...");
                config.load(new FileInputStream(configPath + "ServerListFolder.cfg"));
            } else {
                ServerListFolder.info("No config file found, creating...");
                createConfig(cfg);
            }
        } catch(Exception e) {
            ServerListFolder.warn(e.toString());
        }
    }

    /**
     * Creates a config properties of default values
     * Then saves the config to the config location
     *
     * @param cfg config file
     */
    private void createConfig(File cfg) {
        File folder = new File(configPath);
        if(!folder.exists()) {
            ServerListFolder.info("No folder found, creating...");
            folder.mkdirs();
        }
        
        try {
            cfg.createNewFile();
            config.setProperty("configVersion", ""+version);
            config.setProperty("FolderMotdColor", "purple");
            config.setProperty("FolderMotdText", "Folder");
            config.store(new FileOutputStream(configPath +"ServerListFolder.cfg"),"Server List Folder Mod Config" + "\nUse the color names(without spaces!) form: http://www.minecraftwiki.net/wiki/Color_Codes");
        } catch (Exception e) {
            ServerListFolder.warn(e.toString());
        }
    }

    /**
     * Loads the property data into the local data
     */
    public void loadConfigData() {
        ServerListFolder.info("Loading Config to Local Data");
        configVersion = this.getIntProperty("configVersion");
        FolderMotdColor = this.getStringProperty("FolderMotdColor");
        parseColor();
        FolderMotdText = this.getStringProperty("FolderMotdText");
        checkForConfigUpdate();
    }

    public void setProperty(String prop, String value) {
        config.setProperty(prop, value);
        saveConfig();
    }

    public void setProperty(String prop, float value) {
        String s = String.valueOf(value);
        config.setProperty(prop, s);
        saveConfig();
    }

    public void setProperty(String prop, int value) {
        String s = String.valueOf(value);
        config.setProperty(prop, s);
        saveConfig();
    }

    public void setProperty(String prop, boolean value) {
        String s = String.valueOf(value);
        config.setProperty(prop, s);
        saveConfig();
    }

    public String getStringProperty(String prop) {
        return config.getProperty(prop);
    }

    public float getFloatProperty(String prop) {
        String s = config.getProperty(prop);
        return Float.parseFloat(s);
    }

    public int getIntProperty(String prop) {
        String s = config.getProperty(prop);
        return Integer.parseInt(s);
    }

    public boolean getBoolProperty(String prop) {
        String s = config.getProperty(prop);
        return Boolean.parseBoolean(s);
    }

    public static String getDefaultPropertyValue(String prop) {
        return defaults.getProperty(prop);
    }

    public static float getDefaultFloatProperty(String prop) {
        String s = defaults.getProperty(prop);
        return Float.parseFloat(s);
    }

    public static int getDefaultIntProperty(String prop) {
        String s = defaults.getProperty(prop);
        return Integer.parseInt(s);
    }

    public static boolean getDefaultBoolProperty(String prop) {
        String s = defaults.getProperty(prop);
        return Boolean.parseBoolean(s);
    }

    public void saveConfig() {
        try {
            config.store(new FileOutputStream(configPath + "ServerListFolder.cfg"), null);
            config.load(new FileInputStream(configPath + "ServerListFolder.cfg"));
        } catch (Exception e) {
            ServerListFolder.warn(e.toString());
        }
    }

    /**
     * Checks if the config version has changed and adds the options which are new.
     */
    private void checkForConfigUpdate() {
        if(version != configVersion) {
            ServerListFolder.info("Updating the config...");
            switch(configVersion) {
            case 0:
                if(FolderMotdColor.equals(defaults.getProperty("FolderMotdColor"))) {
                    config.setProperty("FolderMotdColor", defaults.getProperty("FolderMotdColor"));
                }
                if(FolderMotdText.equals(defaults.getProperty("FolderMotdText"))) {
                    config.setProperty("FolderMotdText", defaults.getProperty("FolderMotdText"));
                }
            case 1:
                // for the next version.
            }
            config.setProperty("configVersion", ""+version);
            saveConfig();
        }
    }
    
    private void parseColor() {
        if(FolderMotdColor.equalsIgnoreCase("black")){
            FolderMotdColorCode = EnumChatFormatting.BLACK;
        } else if(FolderMotdColor.equalsIgnoreCase("white")) {
            FolderMotdColorCode = EnumChatFormatting.WHITE;
        } else if(FolderMotdColor.equalsIgnoreCase("red")) {
            FolderMotdColorCode = EnumChatFormatting.RED;
        } else if(FolderMotdColor.equalsIgnoreCase("blue") || FolderMotdColor.equalsIgnoreCase("indigo")) {
            FolderMotdColorCode = EnumChatFormatting.BLUE;
        } else if (FolderMotdColor.equals("darkblue") || FolderMotdColor.equalsIgnoreCase("navy")) {
            FolderMotdColorCode = EnumChatFormatting.DARK_BLUE;
        } else if(FolderMotdColor.equalsIgnoreCase("darkgreen") || FolderMotdColor.equalsIgnoreCase("green")) {
            FolderMotdColorCode = EnumChatFormatting.DARK_GREEN;
        } else if(FolderMotdColor.equalsIgnoreCase("darkaqua") || FolderMotdColor.equalsIgnoreCase("teal")) {
            FolderMotdColorCode = EnumChatFormatting.DARK_AQUA;
        } else if(FolderMotdColor.equalsIgnoreCase("yellow")) {
            FolderMotdColorCode = EnumChatFormatting.YELLOW;
        } else if(FolderMotdColor.equalsIgnoreCase("grey") || FolderMotdColor.equalsIgnoreCase("silver")) {
            FolderMotdColorCode = EnumChatFormatting.GRAY;
        } else if(FolderMotdColor.equalsIgnoreCase("aqua")) {
            FolderMotdColorCode = EnumChatFormatting.AQUA;
        } else if(FolderMotdColor.equalsIgnoreCase("pink")) {
            FolderMotdColorCode = EnumChatFormatting.LIGHT_PURPLE;
        } else if (FolderMotdColor.equalsIgnoreCase("gold") || FolderMotdColor.equalsIgnoreCase("orange")) {
            FolderMotdColorCode = EnumChatFormatting.GOLD;
        } else if(FolderMotdColor.equalsIgnoreCase("lime") || FolderMotdColor.equalsIgnoreCase("brightgreen")) {
            FolderMotdColorCode = EnumChatFormatting.GREEN;
        } else if(FolderMotdColor.equalsIgnoreCase("darkgrey")) {
            FolderMotdColorCode = EnumChatFormatting.DARK_GRAY;
        } else if(FolderMotdColor.equalsIgnoreCase("darkred") || FolderMotdColor.equalsIgnoreCase("maroon")) {
            FolderMotdColorCode = EnumChatFormatting.DARK_RED;
        } else if(FolderMotdColor.equalsIgnoreCase("purple")) {
            FolderMotdColorCode = EnumChatFormatting.DARK_PURPLE;
        } else {
            ServerListFolder.warn("Failed to parse the Color Setting. Using Purple");
            FolderMotdColorCode = EnumChatFormatting.DARK_PURPLE;
        }
    }
}

