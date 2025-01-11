package de.jan.autonick.config;

import de.jan.autonick.AutoNick;

import java.io.*;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class AutoNickConfiguration {

    private FileConfiguration configuration;

    private boolean bungeeCord;

    public AutoNickConfiguration(AutoNick plugin) {
        plugin.saveDefaultConfig();

        this.configuration = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "config.yml"));

        this.bungeeCord = this.getBoolean("bungeecord");
    }

    public String getString(String key) {
        if (!key.equals("prefix")) {
            return ChatColor.translateAlternateColorCodes('&',
                    configuration.getString(key).replace("{PREFIX}", getString("prefix")));
        }
        return ChatColor.translateAlternateColorCodes('&', configuration.getString(key));
    }

    public boolean getBoolean(String key) {
        return configuration.getBoolean(key);
    }

    public int getInteger(String key) {
        return configuration.getInt(key);
    }

    public String getPrefix() {
        return configuration.getString("prefix").replace("&", "§") + " ";
    }

    public boolean isBungeeCord() {
        return bungeeCord;
    }

    public void setBungeeCord(boolean bungeeCord) {
        this.bungeeCord = bungeeCord;
    }

    public int getNickItemSlot() {
        return getInteger("nickItem.slot") - 1;
    }
}