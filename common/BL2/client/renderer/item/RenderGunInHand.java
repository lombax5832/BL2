package BL2.client.renderer.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import BL2.Utils.BL2Texture;
import BL2.client.model.ModelAssaultRifle;
import BL2.client.model.ModelAssaultRifleInv;
import BL2.client.model.ModelPistol;
import BL2.client.model.ModelPistolInv;
import BL2.client.model.ModelSMG;
import BL2.client.model.ModelSMGInv;
import BL2.client.model.ModelSniper;
import BL2.client.model.ModelSniperInv;

/**
 * Class used for rendering the weapon in the holder's hand
 * @author lombax5832
 */
public class RenderGunInHand implements IItemRenderer {

    private ModelPistol pistolModel;
    private ModelSniper sniperModel;
    private ModelSMG smgModel;
    private ModelAssaultRifle assaultRifleModel;

    private ModelSniperInv sniperInv;
    private ModelPistolInv pistolInv;
    private ModelSMGInv smgInv;
    private ModelAssaultRifleInv assaultRifleInv;
    
    private int pistolId = 1;
    private int smgId = 2;
    private int assaultId = 3;
    private int sniperId = 5;

    public RenderGunInHand() {

        sniperInv = new ModelSniperInv();
        pistolInv = new ModelPistolInv();
        smgInv = new ModelSMGInv();
        assaultRifleInv = new ModelAssaultRifleInv();

        smgModel = new ModelSMG();
        pistolModel = new ModelPistol();
        sniperModel = new ModelSniper();
        assaultRifleModel = new ModelAssaultRifle();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {

        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
            ItemRendererHelper helper) {

        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        switch (type) {
            case ENTITY: {
                if (item.getItemDamage() == pistolId) {
                    renderPistol(0F, -1.4F, 0F, 0, 0, 0, 0);
                    break;
                } else if (item.getItemDamage() == smgId) {
                    renderSMG(0F, 0F, 0F, 0, 0, 0, 0);
                    break;
                } else if (item.getItemDamage() == assaultId) {
                    renderAssault(1F, -0.8F, .6F, 0, 0, 0, 0);
                    break;
                } else if (item.getItemDamage() == sniperId) {
                    renderSniper(-.5F, -1.4F, 0F, 0, 0, 0, 0);
                    break;
                }
            }
            case EQUIPPED_FIRST_PERSON: {
                if (item.getItemDamage() == pistolId) {
                    renderPistol(0F, -0.5F, .8F, 1, 1, 0, 0);
                    break;
                } else if (item.getItemDamage() == smgId) {
                    renderSMG(0.1F, .5F, 0.2F, 1, 1, 0, 0);
                    break;
                } else if (item.getItemDamage() == assaultId) {
                    renderAssault(1F, -0.5F, .6F, 1, 1, 0, 0);
                    break;
                } else if (item.getItemDamage() == sniperId) {
                    renderSniper(.6F, -1.4F, 0.65F, 1, 1, 0, 0);
                    break;
                }
            }
            case EQUIPPED: {
                if (item.getItemDamage() == pistolId) {
                    renderPistol(0F, -0.5F, .8F, 100, 0, 1, 0);
                    break;
                } else if (item.getItemDamage() == smgId) {
                    renderSMG(0.3F, .5F, .4F, 100, 0, 1, 0);
                    break;
                } else if (item.getItemDamage() == assaultId) {
                    renderAssault(0.4F, -0.5F, -0.6F, 100, 0, 1, 0);
                    break;
                } else if (item.getItemDamage() == sniperId) {
                    renderSniper(-0.2F, -1.4F, 0F, 85, 0, 1, 0);
                    break;
                }
            }
            case INVENTORY: {
                if (item.getItemDamage() == pistolId) {
                    renderPistolForInventory(.6F, -.9F, 1F);
                    break;
                } else if (item.getItemDamage() == smgId) {
                    renderSMGForInventory(.6F, -0.1F, 0.7F);
                    break;
                } else if (item.getItemDamage() == assaultId) {
                    renderAssaultForInventory(.6F, -0.6F, 0.9F);
                    break;
                } else if (item.getItemDamage() == sniperId) {
                    renderSniperForInventory(.3F, -0.6F, 0.6F);
                    break;
                }
            }
            default:
                break;
        }

    }

    private void renderSMG(float x, float y, float z, float angle, float rotX, float rotY, float rotZ) {
        Minecraft mc = Minecraft.getMinecraft();
        TextureManager render = mc.renderEngine;
        render.bindTexture(BL2Texture.BL2ResourceLocation("textures/items/SMGModel.png"));
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(x, y, z); // size
        GL11.glRotatef(angle, rotX, rotY, rotZ);
        smgModel.render(0.1F);
        GL11.glPopMatrix(); // end
    }

    private void renderSMGForInventory(float x, float y, float z) {
        Minecraft mc = Minecraft.getMinecraft();
        TextureManager render = mc.renderEngine;
        render.bindTexture(BL2Texture.BL2ResourceLocation("textures/items/SMGModel.png"));
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(x, y, z); // size
        smgInv.render(0.094F);
        GL11.glPopMatrix(); // end
    }

    private void renderSniper(float x, float y, float z, float angle, float rotX, float rotY, float rotZ) {
        Minecraft mc = Minecraft.getMinecraft();
        TextureManager render = mc.renderEngine;
        render.bindTexture(BL2Texture.BL2ResourceLocation("textures/items/SniperModel.png"));
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(x, y, z); // size
        GL11.glRotatef(angle, rotX, rotY, rotZ);
        sniperModel.render(0.15F);
        GL11.glPopMatrix(); // end
    }

    private void renderSniperForInventory(float x, float y, float z) {
        Minecraft mc = Minecraft.getMinecraft();
        TextureManager render = mc.renderEngine;
        render.bindTexture(BL2Texture.BL2ResourceLocation("textures/items/SniperModel.png"));
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(x, y, z); // size
        sniperInv.render(.06F);
        GL11.glPopMatrix(); // end
    }

    private void renderPistol(float x, float y, float z, float angle, float rotX, float rotY, float rotZ) {
        Minecraft mc = Minecraft.getMinecraft();
        TextureManager render = mc.renderEngine;
        render.bindTexture(BL2Texture.BL2ResourceLocation("textures/items/PistolModel.png"));
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(x, y, z); // size
        GL11.glRotatef(angle, rotX, rotY, rotZ);
        pistolModel.render(0.1F);
        GL11.glPopMatrix(); // end
    }
    
    private void renderPistolForInventory(float x, float y, float z) {
        Minecraft mc = Minecraft.getMinecraft();
        TextureManager render = mc.renderEngine;
        render.bindTexture(BL2Texture.BL2ResourceLocation("textures/items/PistolModel.png"));
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(x, y, z); // size
        pistolInv.render(0.1F);
        GL11.glPopMatrix(); // end
    }

    private void renderAssault(float x, float y, float z, float angle, float rotX, float rotY, float rotZ) {
        Minecraft mc = Minecraft.getMinecraft();
        TextureManager render = mc.renderEngine;
        render.bindTexture(BL2Texture.BL2ResourceLocation("textures/items/AssaultRifleModel.png"));
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(x, y, z); // size
        GL11.glRotatef(angle, rotX, rotY, rotZ);
        assaultRifleModel.render(0.1F);
        GL11.glPopMatrix(); // end
    }

    private void renderAssaultForInventory(float x, float y, float z) {
        Minecraft mc = Minecraft.getMinecraft();
        TextureManager render = mc.renderEngine;
        render.bindTexture(BL2Texture.BL2ResourceLocation("textures/items/AssaultRifleModel.png"));
        GL11.glPushMatrix(); // start
        GL11.glTranslatef(x, y, z); // size
        assaultRifleInv.render(0.07F);
        GL11.glPopMatrix(); // end
    }

}