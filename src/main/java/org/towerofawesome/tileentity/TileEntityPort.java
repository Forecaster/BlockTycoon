package org.towerofawesome.tileentity;

import com.mojang.authlib.GameProfileRepository;
import ibxm.Player;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import org.towerofawesome.BlockTycoon;
import org.towerofawesome.Controller;

import java.util.UUID;

/**
 * Created by Forecaster on 29/04/2016 for the BlockTycoon project.
 */
public class TileEntityPort extends BlockTycoonTileEntity
{
  public UUID controllerId;
  public UUID claimedBy;
  public int selectedOutput = 0;

  public boolean setAddress(UUID address)
  {
    this.controllerId = address;
    return true;
  }

  public UUID getAddress()
  {
    return this.controllerId;
  }

  @Override
  public int getSizeInventory()
  {
    if (this.controllerId != null && BlockTycoon.controllers.containsKey(this.controllerId))
      return BlockTycoon.controllers.get(this.controllerId).getSizeInventory();
    else
      return 0;
  }

  @Override
  public ItemStack getStackInSlot(int slot)
  {
    if (this.controllerId != null && BlockTycoon.controllers.containsKey(this.controllerId))
      return BlockTycoon.controllers.get(this.controllerId).getStackInSlot(slot);
    else
      return null;
  }

  @Override
  public ItemStack decrStackSize(int slot, int amount)
  {
    if (this.controllerId != null && BlockTycoon.controllers.containsKey(this.controllerId))
    {
      ItemStack stack = BlockTycoon.controllers.get(this.controllerId).decrStackSize(slot, amount);
      if (stack != null)
      {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null)
          tag = new NBTTagCompound();
        tag.setInteger("goods_origin_x", this.xCoord);
        tag.setInteger("goods_origin_z", this.zCoord);
        stack.setTagCompound(tag);
      }
      return stack;
    }
    else
      return null;
  }

  @Override
  public ItemStack getStackInSlotOnClosing(int slot)
  {
    BlockTycoon.log.info("Something called getStackInSlotOnClosing!");
    if (this.controllerId != null && BlockTycoon.controllers.containsKey(this.controllerId))
      return BlockTycoon.controllers.get(this.controllerId).getStackInSlot(slot);
    else
      return null;
  }

  @Override
  public void setInventorySlotContents(int slot, ItemStack stack)
  {
    BlockTycoon.log.info("Something called setInventorySlotContents!");
    if (this.controllerId != null && BlockTycoon.controllers.containsKey(this.controllerId))
      BlockTycoon.controllers.get(this.controllerId).setInventorySlotContents(slot, stack);
  }

  @Override
  public String getInventoryName()
  {
    return "IO Port";
  }

  @Override
  public boolean hasCustomInventoryName()
  {
    return true;
  }

  @Override
  public int getInventoryStackLimit()
  {
    if (this.controllerId != null && BlockTycoon.controllers.containsKey(this.controllerId))
      return BlockTycoon.controllers.get(this.controllerId).getInventoryStackLimit();
    else
      return 0;
  }

  @Override
  public boolean isUseableByPlayer(EntityPlayer player)
  {
    if (this.controllerId != null && BlockTycoon.controllers.containsKey(this.controllerId))
      return BlockTycoon.controllers.get(this.controllerId).isUseableByPlayer(player);
    else
      return false;
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
  public boolean isItemValidForSlot(int slot, ItemStack stack)
  {
    BlockTycoon.log.info("Port received slot validation check for slot " + slot);
    if (this.controllerId != null && BlockTycoon.controllers.containsKey(this.controllerId))
      return BlockTycoon.controllers.get(this.controllerId).isItemValidForSlot(slot, stack);
    else
      return false;
  }

  public void onClick(World world, EntityPlayer player)
  {
    if (!world.isRemote)
    {
      if (this.controllerId != null)
      {
        Controller c = BlockTycoon.controllers.get(this.controllerId);
        if (c != null)
        {
          c.doProduction(player);
          player.addChatMessage(new ChatComponentTranslation("Ran production for linked controller: " + this.controllerId));
        }
        else
          player.addChatMessage(new ChatComponentTranslation("The address " + this.controllerId + " does not match any controller."));
      }
      else player.addChatMessage(new ChatComponentTranslation("No address assigned to this port."));
    }
  }

  @Override
  public void readFromNBT(NBTTagCompound compound)
  {
    super.readFromNBT(compound);
    if (compound.hasKey("controller_address"))
      this.controllerId = UUID.fromString(compound.getString("controller_address"));
    if (compound.hasKey("claimed_by"))
      this.claimedBy = UUID.fromString(compound.getString("claimed_by"));
    if (compound.hasKey("output_index"))
      this.selectedOutput = compound.getInteger("output_index");
    else
      this.selectedOutput = 0;
  }

  @Override
  public void writeToNBT(NBTTagCompound compound)
  {
    super.writeToNBT(compound);
    if (this.controllerId != null)
      compound.setString("controller_address", this.controllerId.toString());
    if (this.claimedBy != null)
      compound.setString("claimed_by", this.claimedBy.toString());
    compound.setInteger("output_index", this.selectedOutput);
  }
}
