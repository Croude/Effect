package at.chroonus.effect.commands;

import at.chroonus.effect.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * All those files are Copyrighted
 */
public class OpenInventoryCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("effects")){
            if(sender instanceof Player){
                Player player = (Player)sender;
                Main.getInstance().getEffectManager().openEffectInventory(player);
                return true;
            }else{
                sender.sendMessage("You must be a Player!");
                return true;
            }
        }
        return false;
    }
}
