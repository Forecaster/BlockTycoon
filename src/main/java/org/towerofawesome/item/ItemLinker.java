package org.towerofawesome.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import org.towerofawesome.BlockTycoon;
import org.towerofawesome.tileentity.TileEntityPort;
import org.towerofawesome.util.References;

import java.util.List;
import java.util.UUID;

/**
 * Created by Forecaster on 05/05/2016 for the BlockTycoon project.
 */
public class ItemLinker extends Item
{
  public String name;

  public ItemLinker()
  {
    this.name = "linker";
    super.setUnlocalizedName(References.MOD_ID + ":" + this.name);
    super.setCreativeTab(CreativeTabs.tabRedstone);
    super.setTextureName(References.MOD_ID + ":" + this.name);
    super.setMaxStackSize(1);
  }

  @Override
  public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
  {
    if (!world.isRemote)
    {
      int addressIndex = getAddress(stack);
      Object[] addresses = BlockTycoon.controllers.keySet().toArray();
      if (addresses.length > 0)
      {
        addressIndex += 1;
        if (addressIndex >= addresses.length)
          addressIndex = 0;
        player.addChatMessage(new ChatComponentTranslation("Linker set to " + addresses[addressIndex].toString()));
      }
      else
        player.addChatMessage(new ChatComponentTranslation("There are no controllers."));
      setAddress(stack, addressIndex);
    }
    return stack;
  }

  @Override
  public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int v, float pies, float trump, float birds)
  {
    if (!world.isRemote)
    {
      int addressIndex = getAddress(stack);
      if (addressIndex >= 0)
      {
        Object[] addresses = BlockTycoon.controllers.keySet().toArray();
        if (addresses.length > addressIndex)
        {
          TileEntity block = world.getTileEntity(x, y, z);
          if (block != null && block instanceof TileEntityPort)
          {
            ((TileEntityPort) block).setAddress((UUID) addresses[addressIndex]);
            player.addChatMessage(new ChatComponentTranslation("Port linked to " + addresses[addressIndex].toString()));
          }
          else
            player.addChatMessage(new ChatComponentTranslation("This is not a Port!"));
        }
        else
        {
          setAddress(stack, -1);
          player.addChatMessage(new ChatComponentTranslation("Invalid address. Please reselect!"));
        }
      }
      else
        player.addChatMessage(new ChatComponentTranslation("Select an address!"));
    }
    return true;
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b)
  {
    int addressIndex = getAddress(stack);
    if (addressIndex != -1)
    {
      Object[] addresses = BlockTycoon.controllers.keySet().toArray();
      list.add(addresses[addressIndex].toString());
    }
    else
      list.add(EnumChatFormatting.RED + "No controller selected.");
    list.add("Use to select controller");
    list.add("shift-click port to link");
    super.addInformation(stack, player, list, b);
  }

  private void setAddress(ItemStack stack, int addressIndex)
  {
    NBTTagCompound compound = stack.getTagCompound();

    if (compound == null)
    {
      compound = new NBTTagCompound();
      stack.setTagCompound(compound);
    }
    compound.setInteger("address", addressIndex);
  }

  public int getAddress(ItemStack stack)
  {
    if (stack.getTagCompound() != null && stack.getTagCompound().hasKey("address"))
      return stack.getTagCompound().getInteger("address");
    else
      return -1;
  }
}
