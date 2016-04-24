package org.towerofawesome.tileentity;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.towerofawesome.BlockTycoon;
import org.towerofawesome.block.BlockTycoonTileEntity;

/**
 * Created by Forecaster on 23/04/2016.
 */
public class TileEntityGenerator extends BlockTycoonTileEntity
{
  @Override
  public void update()
  {
    System.out.println("Hai I'm doing stuff!");
  }
}
