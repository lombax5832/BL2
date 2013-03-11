package BL2;

import java.util.EnumSet;

import buildcraft.api.recipes.RefineryRecipe;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import BL2.client.handler.EridiumBucketHelper;
import BL2.client.handler.NetworkHandlerClient;
import BL2.client.handler.RefinedEridiumBucketHelper;
import BL2.common.CreativeTabBL2;
import BL2.common.Reference;
import BL2.common.block.BlockEridiumFlowing;
import BL2.common.block.BlockEridiumStill;
import BL2.common.block.BlockRefinedEridiumFlowing;
import BL2.common.block.BlockRefinedEridiumStill;
import BL2.common.entity.EntityBullet;
import BL2.common.entity.EntityGrenade;
import BL2.common.handler.IItemTickListener;
import BL2.common.handler.NetworkHandler;
import BL2.common.item.ItemArmorShield;
import BL2.common.item.ItemBandoiler;
import BL2.common.item.ItemBucketEridium;
import BL2.common.item.ItemBucketRefinedEridium;
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
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "BL2", name = "Borderlands 2", version = "1.9 (1.4.6/7)")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, 
clientPacketHandlerSpec = @NetworkMod.SidedPacketHandler(channels = {"bl2"}, packetHandler = NetworkHandlerClient.class),
serverPacketHandlerSpec = @NetworkMod.SidedPacketHandler(channels = {"bl2"}, packetHandler = NetworkHandler.class))
public class BL2Core implements ITickHandler
{
	public static Block eridiumStill;
	public static Block eridiumFlowing;
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

    @SidedProxy(clientSide = "BL2.client.proxy.BL2Client", serverSide= "BL2.common.proxy.BL2Proxy")
    public static BL2Proxy proxy;
    @SidedProxy(clientSide = "BL2.client.handler.NetworkHandlerClient", serverSide = "BL2.common.handler.NetworkHandler")
    public static NetworkHandler nethandler;
    
//    public static CreativeTabBL2 tabBL2 = new CreativeTabBL2(Reference.MOD_ID);
    
    @Mod.PreInit
    public void preInt(FMLPreInitializationEvent event){
		
    }
    
    @Mod.Init
    public void init(FMLInitializationEvent event)
    {
    	proxy.registerKeyBinding();
    	
    	proxy.registerRenderInformation();
    	
    	//LanguageRegistry.addName(new ItemStack(BuildCraftEnergy.engineBlock, 1, 3), "Eridium Engine");
    	
    	EntityRegistry.registerModEntity(EntityBullet.class, "Bullet", 1, this, 64, 10, true);
    	EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", 2, this, 64, 10, true);
    	
        guns = new ItemGun(16000);
        bullets = new ItemBullets(16001);
        bandoiler = new ItemBandoiler(16002);
        shield = new ItemArmorShield(16003, shieldrenderid, 1).setIconIndex(65);
        grenade = new ItemGrenade(16004);
        temp = new ItemTemp(16005);
        eridiumFlowing = new BlockEridiumFlowing(2456);
        eridiumStill = new BlockEridiumStill(2457);
        refinedEridiumFlowing = new BlockRefinedEridiumFlowing(2458);
        refinedEridiumStill = new BlockRefinedEridiumStill(2459);
        
        
        // Register Liquid Blocks
        GameRegistry.registerBlock(eridiumStill, "eridiumStill");
        LanguageRegistry.addName(eridiumStill, "Crude Eridium");
        
        GameRegistry.registerBlock(eridiumFlowing, "eridiumFlowing");
        LanguageRegistry.addName(eridiumFlowing, "Crude Eridium (Flowing)");
        
        GameRegistry.registerBlock(refinedEridiumStill, "refinedEridiumStill");
        LanguageRegistry.addName(refinedEridiumStill, "Refined Eridium");
        
        GameRegistry.registerBlock(refinedEridiumFlowing, "refinedEridiumFlowing");
        LanguageRegistry.addName(refinedEridiumFlowing, "Refined Eridium (Flowing)");
        
        // Liquid Containers
        bucketEridium = new ItemBucketEridium(16006).setIconIndex(5*16).setItemName("bucketEridium").setContainerItem(Item.bucketEmpty);
		LanguageRegistry.addName(bucketEridium, "Crude Eridium Bucket");
		
		bucketRefinedEridium = new ItemBucketRefinedEridium(16007).setIconIndex((5*16)+1).setItemName("bucketRefinedEridium").setContainerItem(Item.bucketEmpty);
		LanguageRegistry.addName(bucketRefinedEridium, "Refined Eridium Bucket");
		
		// Register Liquid API
        eridiumLiquid = LiquidDictionary.getOrCreateLiquid("Eridium", new LiquidStack(eridiumStill, 1));
        refinedEridiumLiquid = LiquidDictionary.getOrCreateLiquid("Refined Eridium", new LiquidStack(refinedEridiumStill, 1));
        
        // Add Refinery API
        RefineryRecipe.registerRefineryRecipe(new RefineryRecipe(LiquidDictionary.getLiquid("Eridium", 5), null, LiquidDictionary.getLiquid("Refined Eridium", 4), 70, 5));
        
        //Register Liquid
		LiquidContainerRegistry.registerLiquid(new LiquidContainerData(LiquidDictionary.getLiquid("Eridium", LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(
				bucketEridium), new ItemStack(Item.bucketEmpty)));
		
		LiquidContainerRegistry.registerLiquid(new LiquidContainerData(LiquidDictionary.getLiquid("Refined Eridium", LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(
				bucketRefinedEridium), new ItemStack(Item.bucketEmpty)));
		
		
		
//        LiquidContainerRegistry.registerLiquid(new LiquidContainerData(liquidstack, new ItemStack(bucket, 1, iter), new ItemStack(Item.bucketEmpty)));
//        LiquidDictionary.getOrCreateLiquid("liquidEridium", null);
        
		MinecraftForge.EVENT_BUS.register(new EridiumBucketHelper());
		MinecraftForge.EVENT_BUS.register(new RefinedEridiumBucketHelper());
		
        LanguageRegistry.addName(guns, "Gun");
        guns.setItemName("stuff");
        //registerHandlers();
        TickRegistry.registerTickHandler(this, Side.SERVER);
        proxy.registerRenderTickHandler();
        proxy.registerItemRenderer();
//        MinecraftForge.EVENT_BUS.register(new RenderShield());
        
        proxy.initRenderingAndTextures();
        
        GameRegistry.addRecipe(new ItemStack(temp), new Object[]
        		{
        	"IWI",
        	"WGW",
        	"IWI",
        	'I', Item.ingotIron,
        	'W', Block.planks,
        	'G', Item.ingotGold
        		});
    }
    
    public void tickStart(EnumSet<TickType> var1, Object... var2)
    {
    	if(var1.contains(TickType.PLAYER))
    	{
    		EntityPlayer ep = (EntityPlayer)var2[0];
    		
    		if(ep.isDead)
    		{
    			return;
    		}
    		
    		for(int i = 0; i < 4; i++)
    		{
    			if(ep.inventory.armorInventory[i] != null && ep.inventory.armorInventory[i].getItem() instanceof IItemTickListener)
    			{
	    			if(((IItemTickListener)ep.inventory.armorInventory[i].getItem()).onTick(ep, ep.inventory.armorInventory[i]))
	    			{
	    			}
    			}
    		}
    		
//    		if(update)
//    		{
//    			ep.openContainer.updateCraftingResults();
//    		}
    	}
    }

	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		
	}

	@Override
	public EnumSet<TickType> ticks() 
	{
		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel() 
	{
		return "BL2";
	}
}