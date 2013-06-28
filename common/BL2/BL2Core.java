package BL2;

import java.util.EnumSet;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.oredict.OreDictionary;
import BL2.block.BL2Blocks;
import BL2.core.config.BL2MainConfig;
import BL2.core.handlers.EntityLivingHandler;
import BL2.core.handlers.GUIHandler;
import BL2.core.handlers.IItemTickListener;
import BL2.core.helper.LogHelper;
import BL2.entity.EntityBullet;
import BL2.entity.EntityGrenade;
import BL2.item.BL2Items;
import BL2.lib.Constants;
import BL2.liquid.BL2Liquid;
import BL2.network.NetworkHandler;
import BL2.network.NetworkHandlerClient;
import BL2.proxy.BL2Proxy;
import BL2.recipe.ModRecipes;
import BL2.tile.TileEntityAmmoCrafter;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION_LONG)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, clientPacketHandlerSpec = @NetworkMod.SidedPacketHandler(channels = { "bl2" }, packetHandler = NetworkHandlerClient.class), serverPacketHandlerSpec = @NetworkMod.SidedPacketHandler(channels = { "bl2" }, packetHandler = NetworkHandler.class))
public class BL2Core implements ITickHandler {

    @Instance("BL2")
    public static BL2Core instance;
    
    public static int crudeEridiumModel;
    public static int refinedEridiumModel;
    public static int shieldModel;

    @SidedProxy(clientSide = "BL2.proxy.BL2ProxyClient", serverSide = "BL2.proxy.BL2Proxy")
    public static BL2Proxy proxy;
    @SidedProxy(clientSide = "BL2.network.NetworkHandlerClient", serverSide = "BL2.network.NetworkHandler")
    public static NetworkHandler nethandler;

    @Mod.PreInit
    public void preInt(FMLPreInitializationEvent event) {
        
        //Initializer the Log Helper
        LogHelper.init();
        
        //Load Configs
        BL2MainConfig.loadConfig(event);
    }

    @Mod.Init
    public void init(FMLInitializationEvent event) {
        proxy.registerKeyBinding();
        
        //Initialize Blocks
        BL2Blocks.initialize();
        
        //Initialize Items
        BL2Items.initialize();
        
        //Initialize LiquidStacks
        BL2Liquid.initialize();
        
        //Oredictionary
        for(int i=1;i<7;i++){
            OreDictionary.registerOre("bullet", new ItemStack(BL2Items.bullets,1,i));
        }
        
        //EntityLivingHandler
        MinecraftForge.EVENT_BUS.register(new EntityLivingHandler());
        
        EntityRegistry.registerModEntity(EntityBullet.class, "Bullet", 1, this,
                64, 10, true);
        EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", 2,
                this, 64, 10, true);
        
        GameRegistry.registerTileEntity(TileEntityAmmoCrafter.class, "tileAmmoCrafter");
        
        NetworkRegistry.instance().registerGuiHandler(this, new GUIHandler());
        
        TickRegistry.registerTickHandler(this, Side.SERVER);
        
        proxy.registerRenderInformation();
        proxy.registerRenderTickHandler();
        proxy.registerItemRenderer();
        proxy.initiateRendering();
    }
    
    @Mod.PostInit
    public void postInit(FMLPostInitializationEvent event){
            GameRegistry.addRecipe(new ItemStack(BL2Items.temp), new Object[] { 
                "IWI",
                "WGW", 
                "IWI", 
                'I', Item.ingotIron, 
                'W', Block.planks, 
                'G', Item.ingotGold });
            
            ModRecipes.addModRecipes();
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
        }
    }
    
    @ForgeSubscribe
    @SideOnly(Side.CLIENT)
    public void textureHook(TextureStitchEvent.Post event) {
        if (event.map == Minecraft.getMinecraft().renderEngine.textureMapItems) {
            LiquidDictionary.getCanonicalLiquid("Crude Eridium").setRenderingIcon(BL2Blocks.crudeEridiumStill.getBlockTextureFromSide(1)).setTextureSheet("/terrain.png");
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