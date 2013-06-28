package BL2.client.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import BL2.container.ContainerAmmoCrafter;
import BL2.item.BL2Items;
import BL2.lib.AmmoOutputs;
import BL2.network.NetworkHandler;
import BL2.tile.TileEntityAmmoCrafter;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiAmmoCrafter extends GuiContainer{
    
    TileEntityAmmoCrafter inventory;
    int ammoIndex = 0;
    protected int barMaxSize = 52;
    protected static final int barColor1 = 0xff00F5FF;
    protected static final int barColor2 = 0xff0003FF;
    
    private static int prevMJ;
    
    public GuiAmmoCrafter (InventoryPlayer inventoryPlayer, TileEntityAmmoCrafter tileEntity) {
        //the container is instanciated and passed to the superclass for handling
        super(new ContainerAmmoCrafter(inventoryPlayer, tileEntity));
        inventory = tileEntity;
        ammoIndex = AmmoOutputs.ammoDmgVals[inventory.getCrafting()];
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
        //draw text and stuff here
        //the parameters for drawString are: string, x, y, color
        fontRenderer.drawString("Ammo Crafter", 8, 6, 4210752);
        //draws "Inventory" or your regional equivalent
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
        
//        if(inventory.MJStored != 0)
        prevMJ = inventory.getEnergy();
//        System.out.println(prevMJ);
        //drawBar(8, 69, inventory.MaxMJStored, prevMJ, barColor1, barColor2);
//        System.out.println(inventory.);
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float gameTicks)
    {
        super.drawScreen(mouseX, mouseY, gameTicks);
        
        drawToolTips(mouseX, mouseY);
    }
    
    protected void drawToolTips(int mouseX, int mouseY){
        if(isPointInRegion(7, 16, 18, 54, mouseX, mouseY))
        {
            List<String> lines = new ArrayList<String>();
            lines.add("Energy");
            lines.add((int)prevMJ+"/"+inventory.MaxMJStored+" MJ");
            drawTooltip(lines, mouseX, mouseY);
        }else if(isPointInRegion(66, 39, 33, 8, mouseX, mouseY))
        {
            List<String> lines = new ArrayList<String>();
            lines.add("Work");
            lines.add((int)inventory.getProgress()+"/"+inventory.maxProgress);
            drawTooltip(lines, mouseX, mouseY);
        }
    }
    
    protected void drawBar(int xOffset, int yOffset, int max, float current, int color, int color2)
    {
        int size = (int) (max > 0 ? current * barMaxSize / max : 0);
        if(size > barMaxSize) size = max;
        if(size < 0) size = 0;
        drawGradientRect(xOffset, yOffset - size, xOffset + 16, yOffset, color, color2);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                int par3) {
        //draw your Gui here, only thing you need to change is the path
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/mods/BL2/textures/gui/AmmoCrafter2.png");
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        int i12;
        int i13;
        
        i12 = inventory.getMJLeftScaled(52);
        i13 = inventory.getWorkLeftScaled(31);
//        System.out.println(i12);
        this.drawTexturedModalRect(x + 8, y + 17 + 52 - i12, 176, 52 - i12, 16, i12);
        
        this.drawTexturedModalRect(x + 67, y + 40, 176, 52, i13, 16);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void initGui ()
    {
        super.initGui();
        int cornerX = (this.width - this.xSize) / 2;
        int cornerY = (this.height - this.ySize) / 2;

        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, cornerX - 120, cornerY, 120, 20, "Previous Ammo Type"));
        this.buttonList.add(new GuiButton(1, cornerX + 176, cornerY, 120, 20, "Next Ammo Type"));
    }
    
    protected void actionPerformed (GuiButton button)
    {
        if (button.id == 0)
        {
            ammoIndex++;
            if (ammoIndex >= AmmoOutputs.ammoOutputs.length-2)
                ammoIndex = 0;
        }
        else if (button.id == 1)
        {
            ammoIndex--;
            if (ammoIndex < 0)
                ammoIndex = AmmoOutputs.ammoOutputs.length-1;
        }
        ItemStack stack = new ItemStack(BL2Items.bullets, 1, AmmoOutputs.ammoOutputs[ammoIndex].getItemDamage());
        inventory.setInventorySlotContents(2, stack);
        inventory.onInventoryChanged();
        inventory.setMode(AmmoOutputs.ammoOutputs[ammoIndex].getItemDamage());
        updateServer(stack);
    }

    void updateServer (ItemStack stack)
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try
        {
            outputStream.writeByte(NetworkHandler.craftingPacketID);
            outputStream.writeInt(inventory.worldObj.provider.dimensionId);
            outputStream.writeInt(inventory.xCoord);
            outputStream.writeInt(inventory.yCoord);
            outputStream.writeInt(inventory.zCoord);
            outputStream.writeShort(stack.itemID);
            outputStream.writeShort(stack.getItemDamage());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "bl2";
        packet.data = bos.toByteArray();
        packet.length = bos.size();

        PacketDispatcher.sendPacketToServer(packet);
    }
    
    protected void drawTooltip(List<String> lines, int x, int y)
    {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glDisable(GL11.GL_LIGHTING);
        
        int tooltipWidth = 0;
        int tempWidth;
        int xStart;
        int yStart;
        
        for(int i = 0; i < lines.size(); i++)
        {
            tempWidth = this.fontRenderer.getStringWidth(lines.get(i));
            
            if(tempWidth > tooltipWidth)
            {
                tooltipWidth = tempWidth;
            }
        }
        
        xStart = x + 12;
        yStart = y - 12;
        int tooltipHeight = 8;
        
        if(lines.size() > 1)
        {
            tooltipHeight += 2 + (lines.size() - 1) * 10;
        }
        
        if(this.guiTop + yStart + tooltipHeight + 6 > this.height)
        {
            yStart = this.height - tooltipHeight - this.guiTop - 6;
        }
        
        this.zLevel = 300.0F;
        itemRenderer.zLevel = 300.0F;
        int color1 = -267386864;
        this.drawGradientRect(xStart - 3, yStart - 4, xStart + tooltipWidth + 3, yStart - 3, color1, color1);
        this.drawGradientRect(xStart - 3, yStart + tooltipHeight + 3, xStart + tooltipWidth + 3, yStart + tooltipHeight + 4, color1, color1);
        this.drawGradientRect(xStart - 3, yStart - 3, xStart + tooltipWidth + 3, yStart + tooltipHeight + 3, color1, color1);
        this.drawGradientRect(xStart - 4, yStart - 3, xStart - 3, yStart + tooltipHeight + 3, color1, color1);
        this.drawGradientRect(xStart + tooltipWidth + 3, yStart - 3, xStart + tooltipWidth + 4, yStart + tooltipHeight + 3, color1, color1);
        int color2 = 1347420415;
        int color3 = (color2 & 16711422) >> 1 | color2 & -16777216;
        this.drawGradientRect(xStart - 3, yStart - 3 + 1, xStart - 3 + 1, yStart + tooltipHeight + 3 - 1, color2, color3);
        this.drawGradientRect(xStart + tooltipWidth + 2, yStart - 3 + 1, xStart + tooltipWidth + 3, yStart + tooltipHeight + 3 - 1, color2, color3);
        this.drawGradientRect(xStart - 3, yStart - 3, xStart + tooltipWidth + 3, yStart - 3 + 1, color2, color2);
        this.drawGradientRect(xStart - 3, yStart + tooltipHeight + 2, xStart + tooltipWidth + 3, yStart + tooltipHeight + 3, color3, color3);
        
        for(int stringIndex = 0; stringIndex < lines.size(); ++stringIndex)
        {
            String line = lines.get(stringIndex);
            
            if(stringIndex == 0)
            {
                line = "\u00a7" + Integer.toHexString(15) + line;
            }
            else
            {
                line = "\u00a77" + line;
            }
            
            this.fontRenderer.drawStringWithShadow(line, xStart, yStart, -1);
            
            if(stringIndex == 0)
            {
                yStart += 2;
            }
            
            yStart += 10;
        }
        
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        
        this.zLevel = 0.0F;
        itemRenderer.zLevel = 0.0F;
    }
}
