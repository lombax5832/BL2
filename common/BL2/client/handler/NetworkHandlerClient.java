package BL2.client.handler;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.src.ModLoader;
import BL2.BL2Core;
import BL2.client.render.ShieldFX;
import BL2.common.entity.EntityGrenade;
import BL2.common.handler.NetworkHandler;
import BL2.common.item.ItemArmorShield.ShieldAtributes;
import BL2.common.item.ItemArmorShield.Vector;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class NetworkHandlerClient extends NetworkHandler {

    /*
     * public void sendParticlePacket(World world, double x, double y, double z,
     * int inventoryIndex) {
     * if(FMLCommonHandler.instance().getEffectiveSide().isServer()) {
     * super.sendParticlePacket(world, x, y, z, inventoryIndex); }else {
     * EntityPlayer player = ModLoader.getMinecraftInstance().thePlayer;
     * ShieldFX fx = new ShieldFX(world, player,
     * player.getCurrentArmor(inventoryIndex), x, y, z, Color.CYAN);
     * ModLoader.getMinecraftInstance().effectRenderer.addEffect(fx); } }
     */

    boolean spawnParts = false;

    @Override
    public void sendReloaderPacket() {
        try {
            ByteArrayOutputStream baout = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baout);
            out.writeByte(reloadPacketID);
            out.close();
            Packet250CustomPayload packet = new Packet250CustomPayload();
            packet.channel = "bl2";
            packet.isChunkDataPacket = false;
            packet.data = baout.toByteArray();
            packet.length = baout.size();

            PacketDispatcher.sendPacketToServer(packet);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Color getColor(int type) {
        switch (type) {
            case 1: {
                return Color.CYAN;
            }
            case 2: {
                return Color.RED;
            }
            case 3: {
                return Color.GREEN;
            }
            case 4: {
                return Color.MAGENTA;
            }
            case 5: {
                return Color.YELLOW;
            }
        }
        return Color.WHITE;
    }

    @Override
    public void onPacketData(INetworkManager manager,
            Packet250CustomPayload packet, Player p) {

        // System.out.println("recieved packet");
        ByteArrayInputStream in = new ByteArrayInputStream(packet.data, 1,
                packet.data.length - 1);

        try {
            EntityPlayer player = null;
            if (packet.data[0] == particlePacketID) {
                // System.out.println("spawned");
                DataInputStream din = new DataInputStream(in);
                int dimension = din.readInt();
                int index = din.readInt();
                int playerID = din.readInt();
                int type = din.readInt();
                double distance = din.readDouble();
                spawnParts = din.readBoolean();
                WorldClient world = Minecraft.getMinecraft().theWorld;
                ItemStack shield = null;
                if (world.provider.dimensionId != dimension)
                    return;
                if (player == null) {
                    player = (EntityPlayer) world.getEntityByID(playerID);

                }
                for (int i = 0; i < 4; i++) {
                    shield = player.inventory.armorItemInSlot(i);

                    if (shield != null) {
                        if (shield.itemID == BL2Core.shield.itemID) {
                            new ShieldAtributes(shield);

                        }
                    }
                }
                if (spawnParts) {

                    if (player == p) {
                        for (int i = 0; i < 10; i++)// particles per tick
                        {
                            Vector v = new Vector(Math.random() * 2 - 1,
                                    Math.random() * 2 - 1,
                                    Math.random() * 2 - 1);
                            v.normalize();
                            ShieldFX fx = new ShieldFX(world, player,
                                    player.getCurrentArmor(index), v.x
                                            * distance, v.y - 2 * distance, v.z
                                            * distance, getColor(type));
                            ModLoader.getMinecraftInstance().effectRenderer
                                    .addEffect(fx);
                        }
                    } else {
                        for (int i = 0; i < 10; i++)// particles per tick
                        {
                            Vector v = new Vector(Math.random() * 2 - 1,
                                    Math.random() * 2 - 1,
                                    Math.random() * 2 - 1);
                            v.normalize();
                            ShieldFX fx = new ShieldFX(world, player,
                                    player.getCurrentArmor(index), v.x
                                            * distance, v.y * (distance + .5),
                                    v.z * distance, getColor(type));
                            ModLoader.getMinecraftInstance().effectRenderer
                                    .addEffect(fx);
                        }
                    }
                }
            } else if (packet.data[0] == grenadePacketID) {
                DataInputStream din = new DataInputStream(in);
                int dimention = din.readInt();
                WorldClient world = Minecraft.getMinecraft().theWorld;

                if (world.provider.dimensionId != dimention)
                    return;

                int gid = din.readInt();
                EntityGrenade grenade = (EntityGrenade) world
                        .getEntityByID(gid);
                if (grenade == null)
                    return;

                String var = din.readUTF();
                if (var.equals("parent")) {
                    grenade.stuckTo = world.getEntityByID(din.readInt());
                } else if (var.equals("homing")) {
                    grenade.homing = din.readBoolean();
                }
            }
        } catch (IOException var22) {
            var22.printStackTrace();
        }
    }
}