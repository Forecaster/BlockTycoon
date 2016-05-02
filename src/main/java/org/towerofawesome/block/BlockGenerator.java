package org.towerofawesome.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.towerofawesome.BlockTycoon;
import org.towerofawesome.util.References;
import org.towerofawesome.tileentity.TileEntityGenerator;

/**
 * Created by Forecaster on 22/04/2016.
 */
public class BlockGenerator extends BlockTycoonBlock implements ITileEntityProvider
{
  public BlockGenerator(String unlocalizedName, Material material)
  {
    super(material);
    this.setBlockName(unlocalizedName);
    this.setBlockTextureName(References.MOD_ID + ":" + unlocalizedName);
    this.setBlockUnbreakable();
    this.setCreativeTab(CreativeTabs.tabRedstone);

    this.isBlockContainer = true;

    BlockTycoon.log.info("Create new instance block with texture name: " + References.MOD_ID + ":" + unlocalizedName);
  }

  @Override
  public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
  {
    BlockTycoon.log.info("Create new TileEntity!");
    return new TileEntityGenerator();
  }

  @Override
  public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float facing, float angle, float angle2)
  {
    TileEntityGenerator generator = (TileEntityGenerator) world.getTileEntity(x, y, z);
    generator.lastClicked(player);
    return true;
  }

  @Override
  public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int dunno)
  {
    //BlockTycoonTileEntity te = (BlockTycoonTileEntity) world.getTileEntity(x, y, z);
    //ItemStack stack;
    //super.breakBlock(world, pos, blockstate);
  }
}
