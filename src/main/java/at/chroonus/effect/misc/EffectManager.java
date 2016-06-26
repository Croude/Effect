/**
 * Class to read out all data from the config
 */

package at.chroonus.effect.misc;

import net.minecraft.server.v1_9_R1.EnumParticle;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chroonus on 20.06.2016
 */
public class EffectManager {

    private HashMap<Integer, Effects> effectList;
    private Inventory effectInv;
    private String invName;
    private int invSize;
    private ItemStack fillMaterial;
    private Effects effects;
    private String name = null;

    private ConfigManager configManager;

    public EffectManager() {
        effectList = new HashMap<>();
        configManager = new ConfigManager();

        loadOptions();
        loadEffects();
        loadEffectConfig();
        loadEffectInv();
    }

    /**
     * loads Main-Options like the title and the size of the Inventory
     */
    private void loadOptions() {
        String name = configManager.getConfiguration().getString("SelectEffectInventory.title.de");
        invSize = configManager.getConfiguration().getInt("SelectEffectInventory.size");
        invName = ChatColor.translateAlternateColorCodes('&', name);
    }

    /**
     * loads all Items for the Effects
     */
    private void loadEffects() {
        for (String name : configManager.getConfiguration().getConfigurationSection("Items").getKeys(false)) {

            this.name = name;
            String displayName = configManager.getConfiguration().getString("Items." + name + ".dname.de");
            Material material = Material.getMaterial(configManager.getConfiguration().getString("Items." + name + ".material"));
            int data = configManager.getConfiguration().getInt("Items." + name + ".data");
            int slot = configManager.getConfiguration().getInt("Items." + name + ".slot");
            List<String> lore = new ArrayList<>();
            for (String string : configManager.getConfiguration().getStringList("Items." + name + ".lore")) {
                lore.add(ChatColor.translateAlternateColorCodes('&', string));
            }

            ItemStack itemStack = new ItemStack(material, 1, (short) data);
            ItemMeta meta = itemStack.getItemMeta();
            meta.setLore(lore);
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
            itemStack.setItemMeta(meta);

            if (name.equals("fill")) {
                this.fillMaterial = itemStack;
            } else {
                effects = new Effects(name, itemStack, slot);
                if (!name.equals("toggleHiddentrue") && !name.equals("clearParticle"))
                    loadEffectConfig();
                effectList.put(slot, effects);
            }
        }
    }

    /**
     * loads the config of all Effects
     */
    private void loadEffectConfig() {
        double dx = configManager.getConfiguration().getDouble("Particles." + name + ".dx");
        double dy = configManager.getConfiguration().getDouble("Particles." + name + ".dy");
        double dz = configManager.getConfiguration().getDouble("Particles." + name + ".dz");
        double speed = configManager.getConfiguration().getDouble("Particles." + name + ".speed");
        int amount = configManager.getConfiguration().getInt("Particles." + name + ".amount");
        double x = configManager.getConfiguration().getDouble("Particles." + name + ".x");
        double y = configManager.getConfiguration().getDouble("Particles." + name + ".y");
        double z = configManager.getConfiguration().getDouble("Particles." + name + ".z");
        EnumParticle particle = EnumParticle.valueOf(configManager.getConfiguration().getString("Particles." + name + ".type"));
        effects.setEffectConfig(dx, dy, dz, speed, amount, x, y, z, particle);
    }

    /**
     * loads the Inventory where all Items will appear and selectable
     */
    private void loadEffectInv() {

        effectInv = Bukkit.createInventory(null, invSize, invName);

        if (effectList.isEmpty())
            return;

        for (Map.Entry<Integer, Effects> effect : effectList.entrySet()) {
            effectInv.setItem(effect.getValue().getSlot(), effect.getValue().getItemStack());
        }
        for (int slot = 0; slot < effectInv.getSize(); slot++) {
            if (effectInv.getItem(slot) == null) {
                effectInv.setItem(slot, this.fillMaterial);
            }
        }
    }

    /**
     * opens the Inventory effectInv to a specific Player
     *
     * @param player for setting the player to show the Inventory
     */

    public void openEffectInventory(Player player) {
        player.openInventory(effectInv);
    }

    public void closeEffectInventory(Player player) {
        player.closeInventory();
    }

    public Inventory getEffectInv() {
        return effectInv;
    }

    public HashMap<Integer, Effects> getEffectList() {
        return effectList;
    }
}

