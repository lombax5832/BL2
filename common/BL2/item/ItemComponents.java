package BL2.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemComponents extends Item{

    public static final String[] itemNames = new String[] { "Shield Core" };
    public static final int[] itemMeta = new int[] { 0 };
    public static final int[] itemStackSizes = new int[] { 64 };
    
    Icon[] icons = new Icon[1];
    
    public ItemComponents(int id) {
        super(id);
        maxStackSize = 64;
        this.setCreativeTab(BL2.creativetab.CreativeTabBL2.tabBL2);
        this.setHasSubtypes(true);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubItems(int i, CreativeTabs tabs, List l) {
        for (int j = 0; j < itemNames.length; j++) {
            ItemStack stack = new ItemStack(this, 1, j);
            l.add(stack);
        }
    }
    
    @Override
    public Icon getIconFromDamage(int par1) {
        return icons[par1];
    }
    
    @Override
    public String getItemDisplayName(ItemStack par1ItemStack){
        return itemNames[par1ItemStack.getItemDamage()];
    }
    
    @Override
    public void registerIcons(IconRegister ir) {
        for (int i = 0; i < 1; i++) {
            icons[i] = ir.registerIcon("BL2:" + itemNames[i]);
        }
    }
    
}
