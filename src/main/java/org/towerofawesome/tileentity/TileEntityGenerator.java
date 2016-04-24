package org.towerofawesome.tileentity;

/**
 * Created by Forecaster on 23/04/2016.
 */
public class TileEntityGenerator extends BlockTycoonTileEntity
{
  @Override
  public boolean canUpdate()
  {
    return true;
  }

  @Override
  public void updateEntity()
  {
    System.out.println("[updateEntity] Hai I'm doing stuff!");
  }
}
