package BL2.client.gui;

import net.minecraft.inventory.Container;
import BL2.tile.base.TileEntityBL2Powered;

public class GuiBL2Powered extends GuiBL2Inventory{

    protected TileEntityBL2Powered inventory;
    
    public GuiBL2Powered(Container par1Container,
            TileEntityBL2Powered tileentity) {
        super(par1Container, tileentity);
        inventory = tileentity;
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        
        
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }
    
    @Override
    protected void drawToolTips(int mouseX, int mouseY){
        super.drawToolTips(mouseX, mouseY);
    }

}
