package org.towerofawesome.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import org.towerofawesome.BlockTycoon;

/**
 * Created by Forecaster on 23/04/2016.
 */
public class TileEntityGenerator extends BlockTycoonTileEntity
{
  private String lastClickedBy;

  @Override
  public boolean canUpdate()
  {
    return false;
  }

  @Override
  public void updateEntity()
  {
    //Work work
  }

  public void message()
  {
    if (!this.worldObj.isRemote)
      BlockTycoon.log.info("I was clicked!");
  }

  public void lastClicked(EntityPlayer player)
  {
    if (!this.worldObj.isRemote)
    {
      if (lastClickedBy != null)
        BlockTycoon.log.info("I was last clicked by " + lastClickedBy);
      else
        BlockTycoon.log.info("I haven't been clicked yet.");
      lastClickedBy = player.getDisplayName();
    }
  }

  @Override
  public void writeToNBT(NBTTagCompound compound)
  {
    super.writeToNBT(compound);
    compound.setString("lastClickedBy", this.lastClickedBy);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound)
  {
    super.readFromNBT(compound);

    if (!compound.getString("lastClickedBy").equals(""))
      this.lastClickedBy = compound.getString("lastClickedBy");
  }
}
