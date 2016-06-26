package at.chroonus.effect;

import at.chroonus.effect.commands.OpenInventoryCommand;
import at.chroonus.effect.listener.InventoryClick;
import at.chroonus.effect.listener.PlayerMove;
import at.chroonus.effect.misc.ConfigManager;
import at.chroonus.effect.misc.EffectManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * Created by Chroonus on 20.06.2016
 */
public class Main extends JavaPlugin {

    private static Main instance;
    private ConfigManager configManager;
    private EffectManager effectManager;
    private ArrayList<String> hideParticles = new ArrayList<>();


    public void onEnable() {
        instance = this;
        init();
    }

    private void init() {
        configManager = new ConfigManager();
        effectManager = new EffectManager();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerMove(), this);
        pm.registerEvents(new InventoryClick(), this);
        getCommand("effects").setExecutor(new OpenInventoryCommand());
    }

    /**
     * @return instance of Main Class
     */
    public static Main getInstance() {
        return instance;
    }

    /**
     * @return instance of ConfigManager Class
     * @see at.chroonus.effect.misc.ConfigManager
     */
    public ConfigManager getConfigManager() {
        return configManager;
    }

    /**
     * @return instance of EffectManager Class
     * @see at.chroonus.effect.misc.EffectManager
     */
    public EffectManager getEffectManager() {
        return effectManager;
    }

    /**
     * @return arrayList of all Players, which disabled the Particles
     */
    public ArrayList<String> getHideParticles() {
        return hideParticles;
    }
}
