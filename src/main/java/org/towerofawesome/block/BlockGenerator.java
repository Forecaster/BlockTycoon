package org.towerofawesome.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.towerofawesome.BlockTycoon;
import org.towerofawesome.References;
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
}
