package BL2.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import BL2.BL2Core;
import BL2.Utils.Orientation;
import BL2.Utils.Position;
import BL2.creativetab.CreativeTabBL2;
import BL2.tile.TileEntityAmmoCrafter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAmmoCrafter extends BlockContainer{

    Icon blockTextureTop;
    Icon blockTextureSide;
    Icon blockTextureFront[] = new Icon[7];
    public int mode = 3;
    World world;
    int thisx;
    int thisy;
    int thisz;
    boolean onGround = false;
    
    public BlockAmmoCrafter(int par1, Material par2Material) {
        super(par1, Material.rock);
        this.setCreativeTab(CreativeTabBL2.tabBL2);
        this.setHardness(5.6F);
        this.setResistance(56.34F);
        this.setStepSound(BlockAmmoCrafter.soundStoneFootstep);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
                    EntityPlayer player, int idk, float what, float these, float are) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            
            this.world = world;
            thisx = x;
            thisy = y;
            thisz = z;
            if (tileEntity == null || player.isSneaking()) {
                    return false;
            }
            player.openGui(BL2Core.instance, 0, world, x, y, z);
            return true;
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
            dropItems(world, x, y, z);
            super.breakBlock(world, x, y, z, par5, par6);
    }
    
    @Override
    public Icon getIcon(int i, int j) {
//        if(this.onGround){
//            TileEntity tile = world.getBlockTileEntity(thisx, thisy, thisz);
//            if(tile != null){
//                if (j == 0 && i == 3)
//                    return blockTextureFront[((BL2Inventory)tile).getMode()];
//        
//                if (i == j)
//                    return blockTextureFront[((BL2Inventory)tile).getMode()];
//            }
//        }else{
        if (j == 0 && i == 3)
            return blockTextureFront[6];

        if (i == j)
            return blockTextureFront[6];
//        }
        switch (i) {
        case 1:
            return blockTextureTop;
        default:
            return blockTextureSide;
        }
    }
    
    public void setMode(int toMode){
        this.mode = toMode;
    }
    
    private void dropItems(World world, int x, int y, int z){
        Random rand = new Random();

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
                return;
        }
            IInventory inventory = (IInventory) tileEntity;
            ItemStack item = inventory.getStackInSlot(0);
    
            if (item != null && item.stackSize > 0) {
                    float rx = rand.nextFloat() * 0.8F + 0.1F;
                    float ry = rand.nextFloat() * 0.8F + 0.1F;
                    float rz = rand.nextFloat() * 0.8F + 0.1F;
    
                    EntityItem entityItem = new EntityItem(world,
                                    x + rx, y + ry, z + rz,
                                    new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));
    
                    if (item.hasTagCompound()) {
                            entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                    }
    
                    float factor = 0.05F;
                    entityItem.motionX = rand.nextGaussian() * factor;
                    entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                    entityItem.motionZ = rand.nextGaussian() * factor;
                    world.spawnEntityInWorld(entityItem);
                    item.stackSize = 0;
            }
    }

    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving, ItemStack stack) {
        super.onBlockPlacedBy(world, i, j, k, entityliving, stack);
        ForgeDirection orientation = Orientation.get2dOrientation(new Position(entityliving.posX, entityliving.posY, entityliving.posZ), new Position(i, j, k));

        world.setBlockMetadataWithNotify(i, j, k, orientation.getOpposite().ordinal(),1);
        this.onGround = true;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityAmmoCrafter();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        blockTextureTop = par1IconRegister.registerIcon("BL2:ammoCrafter_top");
        blockTextureSide = par1IconRegister.registerIcon("BL2:ammoCrafter_side");
        
        for(int i=1;i<7;i++){
            blockTextureFront[i] = par1IconRegister.registerIcon("BL2:ammoCrafter_front_"+i);
        }
    }
}
