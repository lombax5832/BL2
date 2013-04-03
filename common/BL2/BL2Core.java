package BL2;

import java.util.EnumSet;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;
import BL2.client.handler.NetworkHandlerClient;
import BL2.common.entity.EntityBullet;
import BL2.common.entity.EntityGrenade;
import BL2.common.handler.IItemTickListener;
import BL2.common.handler.NetworkHandler;
import BL2.common.item.ItemArmorShield;
import BL2.common.item.ItemBandoiler;
import BL2.common.item.ItemBullets;
import BL2.common.item.ItemGrenade;
import BL2.common.item.ItemGun;
import BL2.common.item.ItemTemp;
import BL2.common.proxy.BL2Proxy;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "BL2", name = "Borderlands 2", version = "1.17 (1.5.1)")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, clientPacketHandlerSpec = @NetworkMod.SidedPacketHandler(channels = { "bl2" }, packetHandler = NetworkHandlerClient.class), serverPacketHandlerSpec = @NetworkMod.SidedPacketHandler(channels = { "bl2" }, packetHandler = NetworkHandler.class))
public class BL2Core implements ITickHandler {
    public static Block crudeEridiumStill;
    public static Block crudeEridiumFlowing;
    public static Block refinedEridiumStill;
    public static Block refinedEridiumFlowing;

    public static Item guns;
    public static Item bullets;
    public static Item bandoiler;
    public static Item shield;
    public static Item grenade;
    public static Item temp;
    public static Item bucketEridium;
    public static Item bucketRefinedEridium;

    public static LiquidStack eridiumLiquid;
    public static LiquidStack refinedEridiumLiquid;

    public static int shieldrenderid = 0;
    public static int crudeEridiumModel;
    public static int refinedEridiumModel;

    @SidedProxy(clientSide = "BL2.client.proxy.BL2Client", serverSide = "BL2.common.proxy.BL2Proxy")
    public static BL2Proxy proxy;
    @SidedProxy(clientSide = "BL2.client.handler.NetworkHandlerClient", serverSide = "BL2.common.handler.NetworkHandler")
    public static NetworkHandler nethandler;

    @Mod.PreInit
    public void preInt(FMLPreInitializationEvent event) {

    }

    @Mod.Init
    public void init(FMLInitializationEvent event) {
        proxy.registerKeyBinding();

        proxy.registerRenderInformation();

        // LanguageRegistry.addName(new ItemStack(BuildCraftEnergy.engineBlock,
        // 1, 3), "Eridium Engine");

        EntityRegistry.registerModEntity(EntityBullet.class, "Bullet", 1, this,
                64, 10, true);
        EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", 2,
                this, 64, 10, true);

        guns = new ItemGun(16000).setUnlocalizedName("Gun");
        bullets = new ItemBullets(16001).setUnlocalizedName("Bullets");
        bandoiler = new ItemBandoiler(16002).setUnlocalizedName("Bandoiler");
        shield = new ItemArmorShield(16003, shieldrenderid, 1)
                .setUnlocalizedName("ItemArmorShield");
        grenade = new ItemGrenade(16004).setUnlocalizedName("Grenade");
        temp = new ItemTemp(16005).setUnlocalizedName("Temp");
        TickRegistry.registerTickHandler(this, Side.SERVER);
        proxy.registerRenderTickHandler();
        proxy.registerItemRenderer();
        proxy.initiateRendering();
        // MinecraftForge.EVENT_BUS.register(new RenderShield());

        GameRegistry.addRecipe(new ItemStack(temp), new Object[] { "IWI",
                "WGW", "IWI", 'I', Item.ingotIron, 'W', Block.planks, 'G',
                Item.ingotGold });
    }

    @Override
    public void tickStart(EnumSet<TickType> var1, Object... var2) {
        if (var1.contains(TickType.PLAYER)) {
            EntityPlayer ep = (EntityPlayer) var2[0];

            if (ep.isDead)
                return;

            for (int i = 0; i < 4; i++) {
                if (ep.inventory.armorInventory[i] != null
                        && ep.inventory.armorInventory[i].getItem() instanceof IItemTickListener) {
                    if (((IItemTickListener) ep.inventory.armorInventory[i]
                            .getItem()).onTick(ep,
                            ep.inventory.armorInventory[i])) {
                    }
                }
            }

            // if(update)
            // {
            // ep.openContainer.updateCraftingResults();
            // }
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {

    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.PLAYER);
    }

    @Override
    public String getLabel() {
        return "BL2";
    }
}