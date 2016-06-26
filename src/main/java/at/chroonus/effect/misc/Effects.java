package at.chroonus.effect.misc;

import net.minecraft.server.v1_9_R1.EnumParticle;
import net.minecraft.server.v1_9_R1.PacketPlayOutWorldParticles;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Chroonus on 20.06.2016
 */
public class Effects{

    private String name;
    private ItemStack itemStack;
    private int slot;
    private double dx, dy, dz, speed, x, y, z;
    private int amount;
    private boolean longDistance = false;
    private EnumParticle type;

    public Effects(String name, ItemStack itemStack, int slot) {
        this.name = name;
        this.itemStack = itemStack;
        this.slot = slot;
        this.dx = 0;
        this.dy = 0;
        this.dz = 0;
        this.speed = 0;
        this.amount = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.type = null;
    }

    public void setEffectConfig(double dx, double dy, double dz, double speed, int amount, double x, double y, double z, EnumParticle type){
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
        this.speed = speed;
        this.amount = amount;
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
    }

    /**
     * Shows the Particle to a specific player
     * @param player for sending the packet to a specific player
     */
    public void sendMoveParticleToPlayer(Player player){
        Location location = player.getLocation();
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(this.type, this.longDistance, (float)(location.getX() + this.x), (float)(location.getY() + this.y), (float)(location.getZ() + this.z), (float)this.dx, (float)this.dy, (float)this.dz, (float)this.speed, this.amount);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }

    public String getName() {
        return name;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getSlot() {
        return slot;
    }

}
