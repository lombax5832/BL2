package BL2.core.id;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * 
* IDAsigner
*
* How to use: Call the assignFirstID() method in the 
* config getBlock or getItem methods. For this to work,
* the config needs to be called in @Init, rather than
* @PreInit. You want to keep the mod configuration file
* field from @PreInit to use in the @Init phase, before
* you construct your items and blocks. This shouldn't assign IDs
* if you install mods after, so you want to only use the assignFirstID()
* method for the default ID parameter on those previous functions.
*
* @author Vazkii
 */
public final class IDAssigner {

  private final boolean blocks;
	private final boolean worldGenBlocks;
	
	private boolean[] takenIDs;
	
	public static final IDAssigner BLOCK_ID_ASSIGNER = new IDAssigner(true);
	public static final IDAssigner ITEM_ID_ASSIGNER = new IDAssigner(false);
	public static final IDAssigner WORLDGEN_BLOCK_ID_ASSIGNER = new IDAssigner();
	
	private IDAssigner(boolean blocks) {
		this.blocks = blocks;
		worldGenBlocks = false;
		takenIDs = new boolean[blocks ? Block.blocksList.length : Item.itemsList.length];
		Arrays.fill(takenIDs, false);
	}
	
	// WorldGen Blocks constructor
	private IDAssigner() {
		blocks = true;
		worldGenBlocks = false;
		takenIDs = new boolean[256];
	}
	
	public void verifyUsedIDs() {
		if(blocks) {
			for(int i = 0; i < (worldGenBlocks ? 256 : Block.blocksList.length); i++) {
				if(Block.blocksList[i] != null)
					takenIDs[i] = true;
			}
		} else {
			for(int i = 0; i < Item.itemsList.length; i++) {
				if(Item.itemsList[i] != null)
					takenIDs[i] = true;
			}
		}
	}
	
	/** Original ID is the ID to put, if no
	 * ID conflicts are found. The enableIDAsigner parameter
	 * should be used as a config node loaded before the ID nodes,
	 * if it's false, the ID asigner won't do anything. **/
	public int assignFirstID(int originalID, boolean enableIDAsigner) {
		if(!enableIDAsigner)
			return originalID;
		
		verifyUsedIDs();
		
		if(!takenIDs[originalID])
			return originalID;
		
		int id = -1;
		
		for(int i = 1; i < takenIDs.length; i++) {
			if(takenIDs[i])
				continue;
			
			id = i;
			break;
		}
		
		if(id == -1)
			throw new IllegalArgumentException("There are no IDs Left! Get less mods man!");
		
		return id;
	}
	
}
