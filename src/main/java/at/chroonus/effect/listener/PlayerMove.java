package at.chroonus.effect.listener;

import at.chroonus.effect.Main;
import at.chroonus.effect.misc.Effects;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * All those files are Copyrighted
 */
public class PlayerMove implements Listener {

    private static Effects effect = null;

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (effect != null && !Main.getInstance().getHideParticles().contains(players.getName())) {
                effect.sendMoveParticleToPlayer(players);
            }
        }
    }

    public static void setEffect(Effects effect) {
        PlayerMove.effect = effect;
    }
}
