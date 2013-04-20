package BL2.creativetab;

import BL2.item.BL2Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabBL2 extends CreativeTabs {

    public static final CreativeTabs tabBL2 = new CreativeTabBL2("bl2");

    public CreativeTabBL2(String label) {
        super(label);
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(BL2Items.temp);
    }

    @Override
    public String getTranslatedTabLabel() {
        return "Borderlands 2";
    }
}
