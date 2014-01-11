package BL2.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.inventory.Container;
import BL2.tile.base.TileEntityBL2Powered;

public class GuiNewAmmoCrafter extends GuiBL2Powered{

    public GuiNewAmmoCrafter(Container par1Container,
            TileEntityBL2Powered tileentity) {
        super(par1Container, tileentity);
    }
    
    @Override
    public void initGui ()
    {
    	super.initGui();
    	int cornerX = (this.width - this.xSize) / 2;
    	int cornerY = (this.height - this.ySize) / 2;

    	this.buttonList.clear();
      	this.buttonList.add(new GuiButton(0, cornerX - 120, cornerY, 120, 20, "Previous Ammo Type"));
      	this.buttonList.add(new GuiButton(1, cornerX + 176, cornerY, 120, 20, "Next Ammo Type"));
      	this.buttonList.add(new GuiButton(2, cornerX + 135, cornerY+33, 30, 20, "On"));
    }

}
