package org.towerofawesome;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import org.towerofawesome.init.ModItems;
import org.towerofawesome.util.References;
import org.towerofawesome.util.Utilities;

import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by Forecaster on 29/04/2016 for the BlockTycoon project.
 * MinecraftServer#getPlayerList().getOppedPlayers().getEntry(EntityPlayer#getGameProfile) != null
 * EntityPlayerMP#interactionManager.getGameType()
 */
public class Controller
{
  UUID controllerId;
  public BuildingType type;
  public long lastInput;
  public HashMap<Product, Integer> inputs = new HashMap<Product, Integer>();
  public HashMap<Product, Integer> outputs = new HashMap<Product, Integer>();
  public int maxCapacityPerInput;
  public int maxCapacityPerOutput;

  public Controller(UUID id, BuildingType type) throws Exception
  {
    if (type == null)
      throw new Exception("type cannot be null!");

    this.lastInput = new Date().getTime();

    this.controllerId = id;
    this.type = type;
    //TODO re-add input output stuff
  }

  public void doProduction()
  {
    this.doProduction(null);
  }

  public void doProduction(@Nullable EntityPlayer player)
  {
    int produced;
    long now = new Date().getTime();
    long maxRunTime = (now - this.lastInput) / 1000;
    this.lastInput = now;

    Product lowestValueIndex = Utilities.getIndexOfLowestValue(this.inputs);

    int minInputStoredAmount = -1;
    int maxProductionTime = -1;
    double minInputProductionTime;
    if (lowestValueIndex != null)
    {
      minInputProductionTime = this.type.inputs.get(this.type.inputs.indexOf(lowestValueIndex)).productionTime;
      minInputStoredAmount = this.inputs.get(lowestValueIndex);

      maxProductionTime = (int) (minInputStoredAmount / minInputProductionTime);

      if (player != null)
      {
        player.addChatMessage(new ChatComponentTranslation("Production would run for at most " + maxProductionTime + " seconds."));
        player.addChatMessage(new ChatComponentTranslation("Smallest stack contains " + minInputStoredAmount + " items"));
      }
    }
    else if (player != null)
      player.addChatMessage(new ChatComponentTranslation("Was not able to find lowest value in inputs."));
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

  public List<Product> getInputs()
  {
    return this.type.inputs;
  }

  public List<Product> getOutputs()
  {
    return this.type.outputs;
  }

  public int getSizeInventory()
  {
    if (this.inputs != null)
      return this.inputs.keySet().toArray().length;
    else
      return 0;
  }

  public ItemStack getStackInSlot(int slot)
  {
    return null;
  }

  public ItemStack decrStackSize(int slot, int amount)
  {
    //BlockTycoon.log.info("Something requested " + amount + " from slot " + slot);
    //for (int i = 0; i < outputs.length; i++)
    //{
    //  if (outputs[i] > 0)
    //  {
    //    outputs[i]--;
    //    ItemStack stack = new ItemStack(ModItems.itemCrate);
    //    stack.stackSize = 1;
    //    NBTTagCompound tag = stack.getTagCompound();
    //    if (tag == null)
    //      tag = new NBTTagCompound();
    //    tag.setString("goods_type", this.type.outputs.get(i).name);
    //    stack.setTagCompound(tag);
    //    return stack;
    //  }
    //}
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
        if (this.type.inputs.get(slot).name.equals(tag.getString("goods_type")))
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
