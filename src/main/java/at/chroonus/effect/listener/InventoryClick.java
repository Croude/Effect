package at.chroonus.effect.listener;

import at.chroonus.effect.Main;
import at.chroonus.effect.misc.Effects;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;

/**
 * All those files are Copyrighted
 */
public class InventoryClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory().equals(Main.getInstance().getEffectManager().getEffectInv())) {
            event.setCancelled(true);
            int slot = event.getSlot();
            HashMap<Integer, Effects> effectList = Main.getInstance().getEffectManager().getEffectList();
            Effects effects = effectList.get(slot);
            if (effects == null) {
                return;
            }
            if (effects.getName().equals("clearParticle"))
                PlayerMove.setEffect(null);
            else if (effects.getName().equals("toggleHiddentrue")) {
                if (Main.getInstance().getHideParticles().contains(player.getName()))
                    Main.getInstance().getHideParticles().remove(player.getName());
                else
                    Main.getInstance().getHideParticles().add(player.getName());
            } else
                PlayerMove.setEffect(effects);
            Main.getInstance().getEffectManager().closeEffectInventory(player);
        }
    }
}