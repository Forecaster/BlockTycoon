package org.towerofawesome.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

/**
 * Created by Forecaster on 23/04/2016.
 */
public abstract class BlockTycoonTileEntity extends BlockContainer
{
  public BlockTycoonTileEntity(Material material)
  {
    super(material);
    this.setBlockName("generator");
  }

  public BlockTycoonTileEntity()
  {
    this(Material.iron);
  }
}
