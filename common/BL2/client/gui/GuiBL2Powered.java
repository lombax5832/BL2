//package BL2.client.gui;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import net.minecraft.inventory.Container;
//import BL2.tile.base.TileEntityBL2Powered;
//
//public class GuiBL2Powered extends GuiBL2Inventory{
//
//    protected TileEntityBL2Powered inventory;
//    
//    public GuiBL2Powered(Container par1Container,
//            TileEntityBL2Powered tileentity) {
//        super(par1Container, tileentity);
//        inventory = tileentity;
//    }
//    
//    @Override
//    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
//        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
//        
//    }
//    
//    @Override
//    public void initGui(){
//        super.initGui();
//    }
//    
//    @Override
//    protected void drawToolTips(int mouseX, int mouseY){
//        super.drawToolTips(mouseX, mouseY);
//        if(isPointInRegion(7, 16, 18, 54, mouseX, mouseY)){
//            List<String> lines = new ArrayList<String>();
//            lines.add("Energy");
//            lines.add((int)inventory.getEnergyStored()+"/"+inventory.getEnergyStoredMax()+" MJ");
//            drawTooltip(lines, mouseX, mouseY);
//        }
//    }
//    
//    @Override
//    protected void drawGuiContainerBackgroundLayer(float par1, int mouseX,
//                int mouseY) {
//        super.drawGuiContainerBackgroundLayer(par1, mouseX, mouseY);
//        int x = (width - xSize) / 2;
//        int y = (height - ySize) / 2;
//        
//        int i1 = inventory.getEnergyLeftScaled(52);
//        
//        this.drawTexturedModalRect(x + 8, y + 17 + 52 - i1, 176, 52 - i1, 16, i1);
//    }
//
//}
