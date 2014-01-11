package BL2.Utils;

import java.util.Locale;

import net.minecraft.util.ResourceLocation;

public class BL2Texture {
	public static ResourceLocation BL2ResourceLocation(String bl2texture){
		
		return new ResourceLocation(BL2.lib.Constants.MOD_ID.toLowerCase(Locale.ENGLISH), bl2texture);
	}
}
