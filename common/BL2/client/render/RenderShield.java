package BL2.client.render;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_FLAT;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glShadeModel;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ForgeSubscribe;
import BL2.BL2Core;
import BL2.common.item.ItemArmorShield;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RenderShield extends GuiIngame {

    public RenderShield(Minecraft par1Minecraft) {
        super(Minecraft.getMinecraft());
        mc = Minecraft.getMinecraft();
    }

    private Minecraft mc;

    @ForgeSubscribe
    @SideOnly(value = Side.CLIENT)
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getMinecraft();

        if (!Minecraft.isGuiEnabled())
            return;

        if (hasShield()) {

            ScaledResolution res = new ScaledResolution(mc.gameSettings,
                    mc.displayWidth, mc.displayHeight);
            int height = res.getScaledHeight();
            int width = res.getScaledWidth();
            int hp = mc.thePlayer.getHealth();
            boolean shouldDrawHUD = mc.playerController.shouldDrawHUD();
            if (shouldDrawHUD && hasShield()) {
                drawSolidGradientRect(width / 2 - 90, height - 42, hp * 4, 10);
            }
        }

    }

    public ItemArmorShield.ShieldAtributes getShield() {
        InventoryPlayer inv = mc.thePlayer.inventory;
        ItemStack stack = null;

        for (int i = 0; i < 4; i++) {
            stack = inv.armorItemInSlot(i);
            if (stack != null && stack.itemID == BL2Core.shield.itemID) {
                ItemArmorShield.ShieldAtributes str = new ItemArmorShield.ShieldAtributes(
                        stack);

                return str;

            }
        }
        return null;
    }

    public boolean hasShield() {
        InventoryPlayer inv = mc.thePlayer.inventory;
        ItemStack stack = null;

        for (int i = 0; i < 4; i++) {
            stack = inv.armorItemInSlot(i);
            if (stack != null && stack.itemID == BL2Core.shield.itemID)
                return true;
        }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderGameOverlay(float par1, boolean par2, int par3, int par4) {
        ScaledResolution res = new ScaledResolution(mc.gameSettings,
                mc.displayWidth, mc.displayHeight);
        int height = res.getScaledHeight();
        int width = res.getScaledWidth();
        int hp = mc.thePlayer.getHealth();
        mc.playerController.shouldDrawHUD();
        drawSolidGradientRect(width / 2 - 90, height - 42, hp * 4, 10);
    }

    public void drawOutlinedBox(int x, int y, int width, int height, int color,
            int outlineColor) {
        glPushMatrix();
        glScalef(0.5F, 0.5F, 0.5F);
        drawSolidRect(x * 2 - 2, y * 2 - 2, (x + width) * 2 + 2,
                (y + height) * 2 + 2, outlineColor);
        drawSolidRect(x * 2 - 1, y * 2 - 1, (x + width) * 2 + 1,
                (y + height) * 2 + 1, color);
        glPopMatrix();
    }

    int zLevel = 0;

    public void drawSolidRect(int vertex1, int vertex2, int vertex3,
            int vertex4, int color) {
        glPushMatrix();
        Color color1 = new Color(0xFF0000);
        Tessellator tess = Tessellator.instance;
        glDisable(GL_TEXTURE_2D);
        tess.startDrawingQuads();
        tess.setColorOpaque(color1.getRed(), color1.getGreen(),
                color1.getBlue());
        tess.addVertex(vertex1, vertex4, zLevel);
        tess.addVertex(vertex3, vertex4, zLevel);
        tess.addVertex(vertex3, vertex2, zLevel);
        tess.addVertex(vertex1, vertex2, zLevel);
        tess.draw();
        glEnable(GL_TEXTURE_2D);
        glPopMatrix();
    }

    public void drawSolidGradientRect(int x, int y, int width, int height) {
        drawSolidGradientRect0(x * 2, y * 2, (x + width) * 2, (y + height) * 2);
    }

    public void drawSolidGradientRect0(int vertex1, int vertex2, int vertex3,
            int vertex4) {
        glPushMatrix();
        glScalef(0.5F, 0.5F, 0.5F);
        glDisable(GL_TEXTURE_2D);
        glDisable(GL_ALPHA_TEST);
        glShadeModel(GL_SMOOTH);
        Color color1Color = Color.RED;
        Color color2Color = Color.BLUE;
        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        tess.setColorOpaque(color1Color.getRed(), color1Color.getGreen(),
                color1Color.getBlue());
        tess.addVertex(vertex1, vertex4, zLevel);
        tess.addVertex(vertex3, vertex4, zLevel);
        tess.setColorOpaque(color2Color.getRed(), color2Color.getGreen(),
                color2Color.getBlue());
        tess.addVertex(vertex3, vertex2, zLevel);
        tess.addVertex(vertex1, vertex2, zLevel);
        tess.draw();
        glShadeModel(GL_FLAT);
        glEnable(GL_ALPHA_TEST);
        glEnable(GL_TEXTURE_2D);
        glPopMatrix();
    }
}
