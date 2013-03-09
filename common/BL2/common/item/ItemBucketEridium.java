package BL2.common.item;

import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import BL2.common.BL2Core;

public class ItemBucketEridium extends ItemBucket {

	public ItemBucketEridium(int i) {
		super(i, BL2Core.eridiumFlowing.blockID);
		iconIndex = 0 * 16 + 1;
		setCreativeTab(BL2Core.tabBL2);
	}

	@Override
	public String getItemDisplayName(ItemStack itemstack) {
		return "Eridium Bucket";
	}

	@Override
	public String getTextureFile()
    {
        return "/BL2/textures/items.png";
    }

}
