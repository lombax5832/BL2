package BL2.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Iterator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import BL2.entity.EntityGrenade;
import BL2.inventory.BL2Inventory;
import BL2.item.BL2Items;
import BL2.item.ItemGun;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class NetworkHandler implements IPacketHandler {
    public static final int particlePacketID = 0;
    public static final int reloadPacketID = 1;
    public static final int craftingPacketID = 4;
    public static final int syncCrafterID = 5;
    public static final int grenadePacketID = 3;

    public void sendToPlayers(Packet packet, World world, int x, int y, int z, int maxDistance) {
        if (packet != null) {
            for (int j = 0; j < world.playerEntities.size(); j++) {
                EntityPlayerMP player = (EntityPlayerMP) world.playerEntities.get(j);

                if (Math.abs(player.posX - x) <= maxDistance && Math.abs(player.posY - y) <= maxDistance && Math.abs(player.posZ - z) <= maxDistance) {
                    player.playerNetServerHandler.sendPacketToPlayer(packet);
                }
            }
        }
    }
    
    public void sendParticlePacket(World world, double distance, int playerID,
            int type, int inventoryIndex, boolean shouldRender) {
        try {
            ByteArrayOutputStream baout = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baout);
            out.writeByte(particlePacketID);
            out.writeInt(world.provider.dimensionId);
            out.writeInt(inventoryIndex);
            out.writeInt(playerID);
            out.writeInt(type);
            out.writeDouble(distance);
            out.writeBoolean(shouldRender);
            out.close();
            Packet250CustomPayload packet = new Packet250CustomPayload();
            packet.channel = "bl2";
            packet.isChunkDataPacket = false;
            packet.data = baout.toByteArray();
            packet.length = baout.size();
            @SuppressWarnings("unchecked")
            Iterator<EntityPlayer> players = world.playerEntities.iterator();

            while (players.hasNext()) {
                EntityPlayer player = players.next();

                Entity hostPlayer = world.getEntityByID(playerID);

                if (player.getDistanceSqToEntity(hostPlayer) < 64.0D) {
                    PacketDispatcher
                            .sendPacketToPlayer(packet, (Player) player);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendReloaderPacket() {

    }

    public void sendGrenadePacket(World world, EntityGrenade grenade,
            String var, Object arg) {
        try {
            ByteArrayOutputStream baout = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baout);
            out.writeByte(grenadePacketID);
            out.writeInt(world.provider.dimensionId);
            out.writeInt(grenade.entityId);
            out.writeUTF(var);
            if (var.equals("parent")) {
                out.writeInt(((Entity) arg).entityId);
            } else if (var.equals("homing")) {
                out.writeBoolean((Boolean) arg);
            }
            out.close();
            Packet250CustomPayload packet = new Packet250CustomPayload();
            packet.channel = "bl2";
            packet.isChunkDataPacket = false;
            packet.data = baout.toByteArray();
            packet.length = baout.size();

            @SuppressWarnings("unchecked")
            Iterator<EntityPlayer> players = world.playerEntities.iterator();

            while (players.hasNext()) {
                EntityPlayer player = players.next();

                if (player.getDistanceSqToEntity(grenade) < 64.0D) {
                    PacketDispatcher
                            .sendPacketToPlayer(packet, (Player) player);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onPacketData(INetworkManager manager,
            Packet250CustomPayload packet, Player p) {
        EntityPlayer player = null;

        ByteArrayInputStream in = new ByteArrayInputStream(packet.data, 1,
                packet.data.length - 1);
        
        DataInputStream inputStream = new DataInputStream(in);

        try {
            switch (packet.data[0]) {
                case NetworkHandler.reloadPacketID: {
                    player = (EntityPlayer) p;

                    ItemStack stack = player.getCurrentEquippedItem();
                    if (stack != null && stack.getItem() == BL2Items.guns) {
                        ItemGun.reload(stack);
                    }
                }
                case NetworkHandler.craftingPacketID: {
                    int dimension = inputStream.readInt();
                    World world = DimensionManager.getWorld(dimension);
                    int x = inputStream.readInt();
                    int y = inputStream.readInt();
                    int z = inputStream.readInt();
                    TileEntity te = world.getBlockTileEntity(x, y, z);

                    Short itemID = inputStream.readShort();
                    Short itemDamage = inputStream.readShort();
                    if (te instanceof BL2Inventory)
                    {
                        ((BL2Inventory) te).setInventorySlotContents(2, new ItemStack(itemID, 1, itemDamage));
                        ((BL2Inventory) te).onInventoryChanged();
                        ((BL2Inventory) te).setMode(itemDamage);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}