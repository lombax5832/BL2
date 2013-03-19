package BL2.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import BL2.BL2Core;

public class ItemBucketEridium extends ItemBucket {

	public ItemBucketEridium(int i) {
		super(i, BL2Core.eridiumFlowing.blockID);
		this.setCreativeTab(BL2.common.CreativeTabBL2.tabBL2);
	}

	@Override
	public String getItemDisplayName(ItemStack itemstack) {
		return "Eridium Bucket";
	}

	@Override
	public void func_94581_a(IconRegister ir) {
		iconIndex = ir.func_94245_a("BL2:" + "EridiumBucket");
	}

}
