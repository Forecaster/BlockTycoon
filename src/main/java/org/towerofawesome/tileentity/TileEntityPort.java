package org.towerofawesome.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
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
  public EntityPlayer claimedBy;

  @Override
  public int getSizeInventory()
  {
    return BlockTycoon.controllers.get(this.controllerId).getInventorySize();
  }

  @Override
  public ItemStack getStackInSlot(int slot)
  {
    return null;
  }

  @Override
  public ItemStack decrStackSize(int slot, int amount)
  {
    return null;
  }

  @Override
  public ItemStack getStackInSlotOnClosing(int slot)
  {
    return null;
  }

  @Override
  public void setInventorySlotContents(int slot, ItemStack stack)
  {

  }

  @Override
  public String getInventoryName()
  {
    return null;
  }

  @Override
  public boolean hasCustomInventoryName()
  {
    return false;
  }

  @Override
  public int getInventoryStackLimit()
  {
    return 0;
  }

  @Override
  public boolean isUseableByPlayer(EntityPlayer player)
  {
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
    return false;
  }

  public void onClick(World world, EntityPlayer player)
  {
    if (world.isRemote)
    {
      //if (claimedBy == null)
      //{
      //  player.addChatMessage(new ChatComponentTranslation("You claimed me!"));
      //  claimedBy = player;
      //}
      //else
      //  player.addChatMessage(new ChatComponentTranslation("Sorry. I'm already claimed by " + claimedBy.getDisplayName()));
      this.controllerId = UUID.fromString("b6165c5a-8bc0-40e3-a81d-d30ca49036a9");
      Controller controller = BlockTycoon.controllers.get(this.controllerId);

      if (controller == null)
      {
        BlockTycoon.controllers.put(this.controllerId, new Controller(this.controllerId, "SomeType"));
        controller = BlockTycoon.controllers.get(this.controllerId);
      }

      InventoryBasic inv = controller.inputs.get(0);

      if (inv == null)
        controller.inputs.add(new InventoryBasic("Input", true, 2));

      int items = controller.getItemsInInventory(0, true);
      player.addChatMessage(new ChatComponentTranslation("This block contains " + items + " items!"));
    }
  }
}
