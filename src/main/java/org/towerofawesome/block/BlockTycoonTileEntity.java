package org.towerofawesome.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import org.towerofawesome.References;

/**
 * Created by Forecaster on 23/04/2016.
 */
public class BlockTycoonTileEntity extends TileEntity implements IUpdatePlayerListBox
{
  @Override
  public boolean canUpdate()
  {
    return true;
  }

  @Override
  public void update()
  {

  }
}
