package at.chroonus.effect.misc;

import at.chroonus.effect.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Chroonus on 20.06.2016
 */
public class ConfigManager {

    private File file;
    private FileConfiguration configuration;

    public ConfigManager() {
        
        file = new File(Main.getInstance().getDataFolder() + File.separator + "config.yml");
        if(!Main.getInstance().getDataFolder().exists())
            Main.getInstance().getDataFolder().mkdir();

        if(!file.exists()) {
            try {
                file.createNewFile();
                configuration = YamlConfiguration.loadConfiguration(file);
                configuration.set("SelectEffectInventory.size", 45);
                configuration.set("SelectEffectInventory.title.de", "&fLobbyperks");
                configuration.set("Items.toggleHiddentrue.slot", 39);
                configuration.set("Items.toggleHiddentrue.dname.de", "&6Effekte deaktivieren");
                configuration.set("Items.toggleHiddentrue.data", 0);
                ArrayList<String> items1 = new ArrayList<>();
                items1.add("");
                items1.add("&7Deaktiviere, dass du jegliche");
                items1.add("&7Effekte deaktivierst");
                configuration.set("Items.toggleHiddentrue.lore", items1);
                configuration.set("Items.toggleHiddentrue.material", "BARRIER");
                configuration.set("Items.clearParticle.slot", 41);
                configuration.set("Items.clearParticle.dname.de", "&6Effekte entfernen");
                configuration.set("Items.clearParticle.data", 0);
                ArrayList<String> items2 = new ArrayList<>();
                items2.add("");
                items2.add("&7Entferne deinen momentanen");
                items2.add("&7Effekt");
                configuration.set("Items.clearParticle.lore", items2);
                configuration.set("Items.clearParticle.material", "INK_SACK");
                configuration.set("Items.fill.dname.de", "&7");
                configuration.set("Items.fill.data", 7);
                configuration.set("Items.fill.material", "STAINED_GLASS_PANE");
                configuration.set("Items.heart.dname.de", "&6Herzen");
                configuration.set("Items.heart.material", "BLAZE_POWDER");
                configuration.set("Items.heart.data", 0);
                configuration.set("Items.heart.slot", 0);
                configuration.set("Particles.heart.dx", 0.5);
                configuration.set("Particles.heart.dy", 0.5);
                configuration.set("Particles.heart.dz", 0.5);
                configuration.set("Particles.heart.speed", 0.25);
                configuration.set("Particles.heart.amount", 4);
                configuration.set("Particles.heart.x", 0.0);
                configuration.set("Particles.heart.y", 0.75);
                configuration.set("Particles.heart.z", 0.0);
                configuration.set("Particles.heart.type", "HEART");
                configuration.set("Particles.heart.dname", "&6Herzen");
                configuration.save(file);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            configuration = YamlConfiguration.loadConfiguration(file);
        }
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }
}
