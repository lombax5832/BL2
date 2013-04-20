package BL2.core.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import BL2.block.BL2Blocks;
import BL2.item.BL2Items;

public class RefinedEridiumBucketHelper {

    @ForgeSubscribe
    public void onBucketFill(FillBucketEvent event) {

        ItemStack result = fillCustomBucket(event.world, event.target);

        if (result == null)
            return;

        event.result = result;
        event.setResult(Result.ALLOW);
    }

    public ItemStack fillCustomBucket(World world, MovingObjectPosition pos) {

        int blockID = world.getBlockId(pos.blockX, pos.blockY, pos.blockZ);

        if ((blockID == BL2Blocks.refinedEridiumStill.blockID || blockID == BL2Blocks.refinedEridiumFlowing.blockID)
                && world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) == 0) {

            world.setBlockMetadataWithNotify(pos.blockX, pos.blockY,
                    pos.blockZ, 0, 2);

            return new ItemStack(BL2Items.bucketRefinedEridium);
        } else
            return null;

    }

}
