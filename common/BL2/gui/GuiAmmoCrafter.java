package BL2.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import BL2.container.ContainerAmmoCrafter;
import BL2.inventory.BL2Inventory;
import BL2.lib.AmmoOutputs;
import BL2.network.NetworkHandler;
import BL2.tile.TileEntityAmmoCrafter;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiAmmoCrafter extends GuiContainer{
    
    BL2Inventory inventory;
    int ammoIndex;
    
    public GuiAmmoCrafter (InventoryPlayer inventoryPlayer, TileEntityAmmoCrafter tileEntity) {
        //the container is instanciated and passed to the superclass for handling
        super(new ContainerAmmoCrafter(inventoryPlayer, tileEntity));
        inventory = tileEntity;
        ammoIndex = 0;
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
        //draw text and stuff here
        //the parameters for drawString are: string, x, y, color
        fontRenderer.drawString("Ammo Crafter", 8, 6, 4210752);
        //draws "Inventory" or your regional equivalent
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                int par3) {
        //draw your Gui here, only thing you need to change is the path
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/mods/BL2/textures/gui/AmmoCrafter.png");
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
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
        ItemStack pattern = inventory.getStackInSlot(0);
        if (pattern != null && pattern.getItem() == new ItemStack(Item.ingotIron).getItem())
        {
            int meta = pattern.getItemDamage();
            if (meta == 0)
            {
                if (button.id == 0)
                {
                    ammoIndex++;
                    if (ammoIndex >= AmmoOutputs.ammoOutputs.length)
                        ammoIndex = 0;
                }
                else if (button.id == 1)
                {
                    ammoIndex--;
                    if (ammoIndex < 0)
                        ammoIndex = AmmoOutputs.ammoOutputs.length-1;
                }
                ItemStack stack = AmmoOutputs.ammoOutputs[ammoIndex];
                int size = AmmoOutputs.ammoOutputsAmt[ammoIndex];
                inventory.setInventorySlotContents(1, stack);
                updateServer(stack, size);
            }
        }
    }

    void updateServer (ItemStack stack, int size)
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
            outputStream.writeShort(size);
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
}
