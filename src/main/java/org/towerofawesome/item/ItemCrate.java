package org.towerofawesome.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import org.towerofawesome.util.References;

/**
 * Created by Forecaster on 05/05/2016 for the BlockTycoon project.
 */
public class ItemCrate extends Item
{
  public String name;

  public ItemCrate()
  {
    this.name = "crate";
    super.setUnlocalizedName("crate");
    super.setCreativeTab(CreativeTabs.tabRedstone);
    super.setTextureName(References.MOD_ID + ":crate");
  }

  @Override
  public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
  {
    NBTTagCompound tag = new NBTTagCompound();
    stack.readFromNBT(tag);
    String myTag = tag.getString("goods_type");

    if (myTag != null)
      player.addChatMessage(new ChatComponentTranslation("This item says \"" + myTag + "\"!"));
    else
    {
      player.addChatMessage(new ChatComponentTranslation("This item said \"bleeeeh\"!"));
      tag.setString("goods_type", "fibbonachi");
      stack.writeToNBT(tag);
    }
    return super.onItemRightClick(stack, world, player);
  }
}
