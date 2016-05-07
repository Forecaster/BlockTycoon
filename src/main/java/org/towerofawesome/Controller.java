package org.towerofawesome;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import org.towerofawesome.init.ModItems;
import org.towerofawesome.util.Config;
import org.towerofawesome.util.References;
import org.towerofawesome.util.Utilities;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Forecaster on 29/04/2016 for the BlockTycoon project.
 */
public class Controller
{
  UUID controllerId;
  public BuildingType type;
  public long lastInput;
  public int[] inputs;
  public int[] outputs;
  public int maxCapacityPerInput;
  public int maxCapacityPerOutput;

  public Controller(UUID id, BuildingType type) throws Exception
  {
    if (type == null)
      throw new Exception("type cannot be null!");

    this.lastInput = new Date().getTime();

    this.controllerId = id;
    this.type = type;
    if (type.inputs != null)
      this.inputs = new int[type.inputs.length];
    else
      this.inputs = null;
    if (type.outputs != null)
      this.outputs = new int[type.outputs.length];
    else
      this.outputs = null;

    if (this.inputs != null)
    {
      for (int i = 0; i < type.inputs.length; i++)
      {
        this.inputs[i] = 10;
      }
    }

    if (this.outputs != null)
    {
      for (int i = 0; i < type.outputs.length; i++)
      {
        this.outputs[i] = 10;
      }
    }
  }

  public void doProduction(@Nullable EntityPlayer player)
  {
    int produced;
    long now = new Date().getTime();
    long maxRunTime = (now - this.lastInput) / 1000;
    this.lastInput = now;

    int maxInputIndex = Utilities.getIndexOfHighestValue(this.inputs);
    double maxInputProductionTime = this.type.inputs[maxInputIndex].productionTime;

    if (player != null)
    {
      player.addChatMessage(new ChatComponentTranslation("Simulating production for " + maxRunTime + " seconds"));
    }
  }

  public boolean setAddress(UUID address)
  {
    this.controllerId = address;
    return true;
  }

  public UUID getAddress()
  {
    return this.controllerId;
  }

  public BuildingType getType()
  {
    return this.type;
  }

  public Product[] getInputs()
  {
    return this.type.inputs;
  }

  public Product[] getOutputs()
  {
    return this.type.outputs;
  }

  public int getSizeInventory()
  {
    if (this.inputs != null)
      return this.inputs.length;
    else
      return 0;
  }

  public ItemStack getStackInSlot(int slot)
  {
    return null;
  }

  public ItemStack decrStackSize(int slot, int amount)
  {
    BlockTycoon.log.info("Something requested " + amount + " from slot " + slot);
    for (int i = 0; i < outputs.length; i++)
    {
      if (outputs[i] > 0)
      {
        outputs[i]--;
        ItemStack stack = new ItemStack(ModItems.itemCrate);
        stack.stackSize = 1;
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null)
          tag = new NBTTagCompound();
        tag.setString("goods_type", this.type.outputs[i].name);
        stack.setTagCompound(tag);
        return stack;
      }
    }
    return null;
  }

  public ItemStack getStackInSlotOnClosing(int slot)
  {
    return this.getStackInSlot(slot);
  }

  public void setInventorySlotContents(int slot, ItemStack stack)
  {
    BlockTycoon.log.info("Something tried to insert " + stack.getUnlocalizedName() + " into slot " + slot);
  }

  public String getInventoryName()
  {
    return "IO Port";
  }

  public boolean hasCustomInventoryName()
  {
    return true;
  }

  public int getInventoryStackLimit()
  {
    return 0;
  }

  public boolean isUseableByPlayer(EntityPlayer player)
  {
    return false;
  }

  public void openInventory()
  {

  }

  public void closeInventory()
  {

  }

  public boolean isItemValidForSlot(int slot, ItemStack stack)
  {
    BlockTycoon.log.info("Query received for " + stack.getUnlocalizedName() + " for slot " + slot);
    if (stack.getUnlocalizedName().equals("item." + References.MOD_ID + ":crate"))
    {
      NBTTagCompound tag = stack.getTagCompound();
      if (tag != null && tag.hasKey("goods_type"))
      {
        if (this.type.inputs[slot].name.equals(tag.getString("goods_type")))
        {
          BlockTycoon.log.info("Goods of type \"" + tag.getString("goods_type") + "\" is valid for slot " + slot);
          return true;
        }
      }
    }
    BlockTycoon.log.info("Item \"" + stack.getUnlocalizedName() + "\" is not valid for slot " + slot);
    return false;
  }
}
