package org.towerofawesome.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.towerofawesome.BlockTycoon;

import static sun.audio.AudioPlayer.player;

/**
 * Created by Forecaster on 23/04/2016.
 */
public class TileEntityGenerator extends BlockTycoonTileEntity
{
  private String lastClickedBy;
  private ItemStack[] inventory;

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

    NBTTagList list = new NBTTagList();
    for (int i = 0; i < this.getSizeInventory(); ++i) {
      if (this.getStackInSlot(i) != null) {
        NBTTagCompound stackTag = new NBTTagCompound();
        stackTag.setByte("Slot", (byte) i);
        this.getStackInSlot(i).writeToNBT(stackTag);
        list.appendTag(stackTag);
      }
    }
    compound.setTag("Items", list);
  }

  @Override
  public void readFromNBT(NBTTagCompound compound)
  {
    super.readFromNBT(compound);

    if (!compound.getString("lastClickedBy").equals(""))
      this.lastClickedBy = compound.getString("lastClickedBy");

    NBTTagList list = compound.getTagList("Items", 10);
    for (int i = 0; i < list.tagCount(); ++i) {
      NBTTagCompound stackTag = list.getCompoundTagAt(i);
      int slot = stackTag.getByte("Slot") & 255;
      this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
    }
  }

  @Override
  public int getSizeInventory()
  {
    return 1;
  }

  @Override
  public ItemStack getStackInSlot(int slot)
  {
    if (slot < 0 || slot >= this.getSizeInventory())
      return null;
    return this.inventory[slot];
  }

  @Override
  public ItemStack decrStackSize(int slot, int amount)
  {
    if (this.getStackInSlot(slot) != null)
    {
      ItemStack stack;

      if (this.getStackInSlot(slot).stackSize >= amount)
      {
        stack = this.getStackInSlot(slot);
        this.setInventorySlotContents(slot, null);
        this.markDirty();
        return stack;
      }
      else
      {
        stack = this.getStackInSlot(slot).splitStack(amount);

        if (this.getStackInSlot(slot).stackSize <= 0)
          this.setInventorySlotContents(slot, null);
        else
          this.setInventorySlotContents(slot, this.getStackInSlot(slot));

        this.markDirty();
        return stack;
      }
    }
    else
      return null;
  }

  @Override
  public ItemStack getStackInSlotOnClosing(int slot)
  {
    ItemStack stack = this.getStackInSlot(slot);
    this.setInventorySlotContents(slot, null);
    return stack;
  }

  @Override
  public void setInventorySlotContents(int slot, ItemStack stack)
  {
    if (slot < 0 || slot >= this.getSizeInventory())
      return;

    if (stack != null && stack.stackSize > this.getInventoryStackLimit())
      stack.stackSize = this.getInventoryStackLimit();

    if (stack != null && stack.stackSize == 0)
      stack = null;

    this.inventory[slot] = stack;
    this.markDirty();
  }

  @Override
  public String getInventoryName()
  {
    return "Generator Inventory";
  }

  @Override
  public boolean hasCustomInventoryName()
  {
    return true;
  }

  @Override
  public int getInventoryStackLimit()
  {
    return 64;
  }

  @Override
  public boolean isUseableByPlayer(EntityPlayer player)
  {
    //From https://github.com/CovertJaguar/Railcraft/blob/master/src/main/java/mods/railcraft/common/blocks/RailcraftTileEntity.java#L45
    if (this.isInvalid())
      return false;
    if (this.getWorldObj().getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this)
      return false;
    return player.getDistanceSq(this.xCoord, this.yCoord, this.zCoord) <= 64;
  }

  @Override
  public void openInventory()
  {

  }

  @Override
  public void closeInventory()
  {

  }

  @Override
  public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
  {
    return true;
  }

  public void clear()
  {
    for (int i = 0; i < this.getSizeInventory(); i++)
    {
      this.setInventorySlotContents(i, null);
    }
  }
}
