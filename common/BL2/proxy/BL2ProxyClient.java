package BL2.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import BL2.BL2Core;
import BL2.client.renderer.item.RenderBullet;
import BL2.client.renderer.item.RenderCrudeEridium;
import BL2.client.renderer.item.RenderGrenade;
import BL2.client.renderer.item.RenderGrenadeInHand;
import BL2.client.renderer.item.RenderGunInHand;
import BL2.core.handlers.ShieldGUIHandler;
import BL2.entity.EntityBullet;
import BL2.entity.EntityGrenade;
import BL2.item.BL2Items;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class BL2ProxyClient extends BL2Proxy {

    @Override
    public void registerRenderInformation() {
        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class,
                new RenderBullet());
        BL2Core.shieldModel = RenderingRegistry
                .addNewArmourRendererPrefix("/BL2/textures");
        RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class,
                new RenderGrenade());
    }

    @Override
    public void registerItemRenderer() {
        MinecraftForgeClient.registerItemRenderer(BL2Items.guns.itemID,
                new RenderGunInHand());
        MinecraftForgeClient.registerItemRenderer(BL2Items.grenade.itemID,
                new RenderGrenadeInHand());
    }

    @Override
    public void initiateRendering() {
        BL2Core.crudeEridiumModel = RenderingRegistry
                .getNextAvailableRenderId();
        
        BL2Core.shieldModel = RenderingRegistry
                .getNextAvailableRenderId();
        
        RenderingRegistry.registerBlockHandler(new RenderCrudeEridium());
    }

    @Override
    public void initItemRenderer(int itemID) {
        if (itemID == BL2Items.guns.itemID) {
            MinecraftForgeClient.registerItemRenderer(itemID,
                    new RenderGunInHand());
        }
        if (itemID == BL2Items.grenade.itemID) {
            MinecraftForgeClient.registerItemRenderer(itemID,
                    new RenderGrenadeInHand());
        }
    }

    @Override
    public void registerRenderTickHandler() {

        TickRegistry.registerTickHandler(new ShieldGUIHandler(), Side.CLIENT);
    }

    @Override
    public void registerKeyBinding() {
        KeyBindingRegistry
                .registerKeyBinding(new BL2.core.handlers.BL2KeyHandler());
    }
}
